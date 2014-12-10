<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>无锡市第四人民医院医疗行为管理系统-健康普及</title>
    
    <meta name="viewport" content="width=100%; initial-scale=1; maximum-scale=1; minimum-scale=1; user-scalable=no;" />
    <link rel="icon" href="menus/images/favicon.png" type="image/png" />
    <link rel="shortcut icon" href="menus/images/favicon.png" type="image/png" />
    <title>Home Page</title>
    <link href="menus/css/bootstrap.css" type="text/css" rel="stylesheet" />
    <link href="menus/css/style.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="menus/js/jquery.min.js"></script>
    <script type="text/javascript" src="menus/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="menus/js/jquery.easing.1.3.js"></script>
    <script type="text/javascript" src="menus/js/jquery.quicksand.js"></script>
    <script type="text/javascript" src="menus/js/superfish.js"></script>
    <script type="text/javascript" src="menus/js/hoverIntent.js"></script>
    <script type="text/javascript" src="menus/js/jquery.hoverdir.js"></script>
    <script type="text/javascript" src="menus/js/jflickrfeed.min.js"></script>
    <script type="text/javascript" src="menus/js/jquery.prettyPhoto.js"></script>
    <script type="text/javascript" src="menus/js/jquery.elastislide.js"></script>
    <script type="text/javascript" src="menus/js/jquery.tweet.js"></script>
    <script type="text/javascript" src="menus/js/smoothscroll.js"></script>
    <script type="text/javascript" src="menus/js/jquery.ui.totop.js"></script>
    <script type="text/javascript" src="menus/js/main.js"></script>
    <script type="text/javascript" src="menus/js/ajax-mail.js"></script>
    <script type="text/javascript" src="menus/js/accordion.settings.js"></script>
    <script type="text/javascript" src="menus/js/jquery.flexslider-min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body>

<header id="header1">
    <!-- <div id="top-menu">
        <div id="social" class="span4 pull-right">

            <div id="vimeo"> <a id="vimeo_img" href="#">Vimeo</a></div>
            <div id="lin"> <a id="lin_img" href="#">Linkedin</a></div>
            <div id="twitter"> <a id="twitter_img" href="#">Twitter</a></div>
            <div id="facebook"> <a id="facebook_img" href="#">Facebook</a></div>
            <div id="rss"> <a id="rss_img" href="#">Rss</a></div>
            <div id="gplus"> <a id="gplus_img" href="#">Google plus</a></div>
        </div>
    </div> -->
   <!--  <div class="container">
        <div class="row">
            <div class="span3 logo">
                <a href="./index.html"><img src="images/logo.png" alt="logo" /></a>
            </div>
            <div class="span5 offset7 login-form hidden-phone"><p class="pull-right">Call us <strong>+1 800 PHONE</strong> or log in to clients support area. </p>
                <form class="bs-docs-example form-inline pull-right" />
                    <input type="text" placeholder="Email" class="input-small" />
                    <input type="password" placeholder="Password" class="input-small" />
                    <button class="btn" type="submit">Sign in</button>
                </form></div>
        </div>
    </div> -->
</header>
<!-- <div style="width: 100%;height: 100%;"><img id="healthImg" width="100%" height="100%" alt="" src="/menus/images/72b1OOOPIC9c.jpg"></div> -->
<section id="main-menu">
    <nav class="container" id="menu">
        <ul id="health_menu">
<!--             <li><a href="#">Page</a> -->
<!--                 <ul> -->
<!--                     <li><a href="#">About Us</a></li> -->
<!--                     <li class="last"><a href="#">Our Services</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
<!--             <li><a href="#">Portfolio</a> -->
<!--                 <ul> -->
<!--                     <li><a href="#">Submenu 1</a> -->
<!--                     </li> -->
<!--                     <li><a href="#">Submenu 3</a> -->
<!--                         <ul> -->
<!--                             <li><a href="#">2nd Level Item</a></li> -->
<!--                             <li class="last"><a href="#">2nd Level Item</a></li> -->
<!--                         </ul> -->
<!--                     </li> -->
<!--                     <li class="last"><a href="#">Submenu 3</a> -->
<!--                     </li> -->
<!--                 </ul> -->
<!--             </li> -->
<!--             <li><a href="#">Blog</a> -->
<!--                 <ul> -->
<!--                     <li><a href="#">Blog</a></li> -->
<!--                     <li class="last"><a href="#">Blog Single</a></li> -->
<!--                 </ul> -->
<!--             </li> -->
<!--             <li><a href="#">Contact</a></li> -->
        </ul>
    </nav>
</section>
<section id="health_content" style="display: none;">
	
</section>
<section id="slider">
    <div class="flexslider">
        <ul class="slides" id="healthIndex_image">
<!--             <li> -->
<!--                 <img src="menus/images/slides/slide3.jpg" /> -->
<!--             </li> -->
<!--             <li> -->
<!--                 <img src="menus/images/slides/slide2.jpg" /> -->
<!--             </li> -->
<!--             <li> -->
<!--                 <img src="menus/images/slides/slide1.jpg" /> -->
<!--             </li> -->
        </ul>
    </div>
</section>

<script>
    $(window).load(function() {
        $('.flexslider').flexslider({
            animation: "slide"
        });
    });
    var menus;
    $(function(){
    	$('.res-menu').change(function(){
    		var index=$('.res-menu').val();
    		showImage(index);
    	});
    	$.ajax({    
            url : 'stat/get_all_menus.action',    
            type : 'post',    
            dataType : 'json',    
            success : function(data) {
            	if(data.message=='success'){
            		menus=data.menus;
            		for(var i=0;i<menus.length;i++){
            			$('#health_menu').append('<li><a href="#" onclick="showImage('+i+');"><h1>'+menus[i].menusName+'</h1></a></li>');
            			$('.res-menu').append('<option value="'+i+'">'+menus[i].menusName+'</option>');
//             			if(i==0){
//             				$('#health_menu').append('<li><a href="#"><h3>'+menus[i].menusName+'</h3></a></li>');
//             			}else{
//             				$('#health_menu's).append('<li><a href="#"><h3>'+menus[i].menusName+'</h3></a></li>');
//             			}
            		}
            		var list=menus[0].list;
            		for(var i=0;i<list.length;i++){
            			$('#healthIndex_image').append('<li><img src="'+list[i].imagePath+'" /></li>');
            		}
            		 $('.flexslider').flexslider({
            	            animation: "slide"
            	        });
            	}else{
            		alert('菜单读取失败');
            	}
            }
        }); 
    	
    	
    });
    
    function showImage(index){
    	$('#slider').empty();
    	$('#slider').append('<div class="flexslider"><div class="flex-viewport" style="overflow: hidden; position: relative;"><ul class="slides" id="healthIndex_image"> </ul></div></div>');
//     	$('#healthIndex_image').empty();
//     	$('#healthIndex_image').html('');
    	var list=menus[index].list;
    	//$(".flexslider").html('<ul class="slides" id="healthIndex_image"></ul>');
		for(var i=0;i<list.length;i++){
			$('#healthIndex_image').append('<li><img src="'+list[i].imagePath+'" /><p align="center"><a href="#" onclick="showPage('+index+','+i+');"><font class="carousel-caption" style="position: absolute; top: 200px; left: 100px;">'+list[i].title+'</font></a></p></li>');
// 			$('#healthIndex_image').append('<li><img src="'+list[i].imagePath+'" /></li>');
		}
		 $('.flexslider').flexslider({
	            animation: "slide"
	     });
    }
    
    function showPage(index,i){
    	$('#slider').html(menus[index].list[i].jspContent);
    }
    
</script>

</body>
</html>