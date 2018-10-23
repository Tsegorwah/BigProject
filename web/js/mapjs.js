/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload=function(){
   var load=new Vue({
       el:'#loading',
       methods:{
           isChrome:function(){      
         var userAgent = navigator.userAgent;
         if (userAgent.indexOf("Chrome") > -1){
             $("#loading").fadeOut();
     } else{
           $("#loading").text("与此浏览器版本不兼容，请使用Chrome浏览器");
            setTimeout(function(){$("#loading").fadeOut();},3000);
           }
           }
       }
   });
   load.isChrome();
};
var head=new Vue({
    el:'#head',
    data:{
        logname:"登陆",
        regname:"注册"
    }
});
var selectmap=new Vue({
    el:'#selectmap',
    data:{
     hotshow:false,
     postshow:false,
     cityname:''
    },
    methods:{ 
       hotblock:function(){    
            this.hotshow=true;
       },
        getcityname:function(e){
            cityname=e.target.innerText;
            this.cityname=cityname;
       },
       hotclose:function(){
           this.hotshow=false;
       },
       postclose:function(){
           this.postshow=false;
       },
       postblock:function(){
           this.postshow=true;
       }
    }
});
 $(function(){
 $(".mapbt").mouseover(function(){
  $(this).animate({fontSize: "20px",marginLeft:"10px",marginRight:"10px"},"fast");});
  $(".mapbt").mouseout(function(){
  $(this).animate({fontSize: "16px",marginLeft:"2px",marginRight:"2px"},"fast");});
  $(".ltbt").mouseover(function(){
  $(this).animate({height: "80px",width:"80px",marginLeft:"10px",marginRight:"10px"},"fast");});
  $(".ltbt").mouseout(function(){
  $(this).animate({height: "60px",width:"60px",marginLeft:"2px",marginRight:"2px"},"fast");});
 });

