import numpy as np
from PIL import Image, ImageDraw, ImageFilter, ImageFont, ImageEnhance

PW, PH = 1500, 2000
AW = PW * 2
MARGIN = 100
CANVAS = (AW + MARGIN * 2, PH + MARGIN * 2)
CREAM = (245, 239, 229)

photo = Image.open('/workspace/poster_src/real_half.jpg').convert('RGB')
illus = Image.open('/workspace/poster_src/illus_half.jpg').convert('RGB')

# ---- crop away the generator's watermark strip at the bottom ----
photo = photo.crop((0, 0, photo.width, photo.height - 145))

# ---- trim the illustration's white paper border ----
arr = np.asarray(illus).astype(np.int16)
near_white = (arr.min(axis=2) > 238)
col_frac = near_white.mean(axis=0)
row_frac = near_white.mean(axis=1)
xs = np.where(col_frac < 0.6)[0]
ys = np.where(row_frac < 0.6)[0]
pad = 2
x0, x1 = max(0, xs.min() - pad), min(illus.width, xs.max() + pad)
y0, y1 = max(0, ys.min() - pad), min(illus.height, ys.max() + pad - 58)
illus_c = illus.crop((x0, y0, x1, y1))
print('trimmed illustration:', illus_c.size)


def cover(im, w, h):
    s = max(w / im.width, h / im.height)
    im2 = im.resize((round(im.width * s), round(im.height * s)), Image.LANCZOS)
    l = (im2.width - w) // 2
    t = (im2.height - h) // 2
    return im2.crop((l, t, l + w, t + h))


photo = cover(photo, PW, PH)
illus_c = cover(illus_c, PW, PH)

# subtle tonal harmonising
photo = ImageEnhance.Color(photo).enhance(0.97)
photo = ImageEnhance.Contrast(photo).enhance(0.985)
photo = Image.blend(photo, Image.new('RGB', photo.size, (255, 243, 228)), 0.03)

illus_c = ImageEnhance.Color(illus_c).enhance(1.08)
illus_c = ImageEnhance.Contrast(illus_c).enhance(1.06)
illus_c = Image.blend(illus_c, Image.new('RGB', illus_c.size, (253, 242, 225)), 0.06)

# ---- artwork: photo left, illustration right, torn-paper seam ----
SHIFT = 1470
illus_panel = cover(illus_c, AW - SHIFT, PH)          # a touch wider so it can bleed left
painting = Image.new('RGB', (AW, PH), (255, 255, 255))
painting.paste(illus_panel, (SHIFT, 0))
photo_layer = Image.new('RGB', (AW, PH), (255, 255, 255))
photo_layer.paste(photo, (0, 0))

yy, xx = np.mgrid[0:PH, 0:AW].astype(np.float32)
rng = np.random.default_rng(3)
fiber = np.convolve(rng.normal(0, 2.2, PH), np.ones(9) / 9, mode='same')
edge_y = (1489.0
          + 15.0 * np.sin(yy[:, 0] / 210.0)
          + 7.0 * np.sin(yy[:, 0] / 63.0 + 1.7)
          + 3.0 * np.sin(yy[:, 0] / 29.0 + 4.1)
          + fiber)[:, None]
alpha = np.clip((xx - edge_y) / 1.6, 0, 1)             # 1 = painting side
alpha_img = Image.fromarray((alpha * 255).astype(np.uint8), 'L')

# soft shadow cast by the paper edge onto the photo
sh = np.clip((edge_y - xx) / 24.0, 0, 1)               # 1 = photo side near edge
shadow_mask = Image.fromarray((sh * 255).astype(np.uint8), 'L').filter(ImageFilter.GaussianBlur(7))
darker = ImageEnhance.Brightness(photo_layer).enhance(0.84)
photo_layer = Image.composite(darker, photo_layer, shadow_mask.point(lambda v: int(v * 0.6)))

art = photo_layer.copy()
art.paste(painting, (0, 0), alpha_img)

# paper-edge highlight
line = Image.new('L', (AW, PH), 0)
ld = ImageDraw.Draw(line)
pts = [(float(e), y) for y, e in zip(range(0, PH + 4, 3), np.append(edge_y[::3], edge_y[-1]))]
ld.line(pts, fill=78, width=2)
line = line.filter(ImageFilter.GaussianBlur(1.4))
art = Image.composite(Image.new('RGB', (AW, PH), (253, 247, 236)), art, line)

# ---- paper canvas ----
canvas = Image.new('RGB', CANVAS, CREAM)
noise = np.random.default_rng(7).normal(0, 4.0, (CANVAS[1], CANVAS[0], 1))
noise = np.repeat(noise, 3, axis=2)
canvas = Image.fromarray(np.clip(np.asarray(canvas).astype(np.float32) + noise, 0, 255).astype(np.uint8), 'RGB')

shadow = Image.new('L', CANVAS, 0)
sd = ImageDraw.Draw(shadow)
sd.rectangle([MARGIN - 4, MARGIN + 4, MARGIN + AW + 4, MARGIN + PH + 12], fill=90)
shadow = shadow.filter(ImageFilter.GaussianBlur(30))
canvas = Image.composite(Image.new('RGB', CANVAS, (168, 152, 130)), canvas,
                         shadow.point(lambda v: int(v * 0.5)))

canvas.paste(art, (MARGIN, MARGIN))

# ---- caption ----
d = ImageDraw.Draw(canvas)
font_path = '/usr/share/fonts/opentype/noto-cjk-otf/NotoSerifCJKsc-Regular.otf'
size, tracking = 48, 14
text = '三十年 · 花田之约'
try:
    font = ImageFont.truetype(font_path, size)
except Exception:
    font = ImageFont.load_default()

total = sum(d.textlength(c, font=font) for c in text) + tracking * (len(text) - 1)
fy = MARGIN + PH + 30
x = (CANVAS[0] - total) / 2
for ch in text:
    d.text((x, fy), ch, font=font, fill=(104, 89, 71))
    x += d.textlength(ch, font=font) + tracking

ly = fy + size / 2 + 1
d.line([(CANVAS[0] / 2 - total / 2 - 52, ly), (CANVAS[0] / 2 - total / 2 - 14, ly)], fill=(188, 173, 152), width=2)
d.line([(CANVAS[0] / 2 + total / 2 + 14, ly), (CANVAS[0] / 2 + total / 2 + 52, ly)], fill=(188, 173, 152), width=2)

d.rectangle([MARGIN - 1, MARGIN - 1, MARGIN + AW, MARGIN + PH], outline=(216, 203, 184), width=2)

out = '/workspace/三十年花田_实拍与手绘拼接海报.jpg'
canvas.save(out, quality=95, subsampling=0)
print('saved', out, canvas.size)
