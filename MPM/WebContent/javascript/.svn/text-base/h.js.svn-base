/* author: zmm, date: Fri Nov 11 2011 16:16:01 GMT+0800 (CST) */


var G={};

(function(j,i,l){function p(c,i){c.addEventListener("load",i,!1);c.addEventListener("error",function(){G.log("404 error:",c.src);i()},!1)}function q(c,i){i.call(null,c);if(c in F){for(var j=F[c],l=0,n=j.length;l<n;l++)j[l].call(null,c);delete F[c]}delete C[c];delete O[c]}function y(c,i){var j=C[c];if(j.deps)for(var l=!1,n=function(){if(!l){var n=X(j.deps);n&&(x[c]=j.wrap.apply(null,n),q(c,i),l=!0)}},p=0,y=j.deps.length;p<y;p++)J(j.deps[p],n);else x[c]=j.wrap.apply(),q(c,i)}function X(c){for(var i=
[],j=0,l=c.length;j<l;j++){var n=c[j],p=x[n];if(n in x)i.push(p);else return!1}return i}function I(c){var i=c.slice(0,1),j=i==="/";i==="."?c=P.relativeUrl+c.slice(1):j||(c=P.url+c);c.split(-3)!==".js"&&(c+=".js");return c}function J(c,i){c in x?i.call(null,c):c in O?(F[c]=F[c]||[],F[c].push(i)):(O[c]=!0,c in C?y(c,i):G.loadScript(I(c),function(){c in x?q(c,i):c in C?y(c,i):(G.log("Module: "+c+" is not defined!"),x[c]=l,q(c,i))}))}function K(c){if(c in x)return x[c];var i=C[c];if(i){var j=i.deps,l=
[];if(j)for(var n=0,p=j.length;n<p;n++)l.push(K(j[n]));x[c]=i.wrap.apply(null,l);delete C[c];return x[c]}else G.log("Module "+c+" is not loaded!")}var O={},F={},C={},x={},P={url:"/js/lib/",relativeUrl:"/js/"},D=Array.prototype,n=Object.prototype,j=String.prototype,c=Array.isArray,S=D.filter,W=D.every,T=D.some,M=D.map,Y=n.toString,fa=/^[^<]*(<[\w\W]+>)[^>]*$/;G.ua=function(){var c=!+"\v1",j=c&&!("maxHeight"in i.body.style),l=c&&"prototype"in Image;return{isIE:c,isIE6:j,isIE8:l,isIE7:c&&!j&&!l,
isIE9:$.browser.msie&&$.browser.version=="9.0",isOpera:$.browser.opera}}();G.ua.isIE&&(p=function(c,i){c.attachEvent("onreadystatechange",function(){var j=c.readyState;(j==="loaded"||j==="complete")&&i()})});G.loadScript=function(c,i){var j=document.createElement("script"),l=document.getElementsByTagName("head")[0];p(j,function(){i&&i.call(j);try{if(j.clearAttributes)j.clearAttributes();else for(var c in j)delete j[c]}catch(h){}l.removeChild(j)});j.async=!0;j.src=c;j.type="text/javascript";return l.insertBefore(j,
l.firstChild)};G.isFun=function(c){return Y.call(c).slice(8,-1)==="Function"};G.isArray=c||function(c){return Y.call(c)==="[object Array]"};G.isNumber=function(c){return!!(c===0||c&&c.toExponential&&c.toFixed)};G.isHtml=function(c){return fa.test(c)};G.each=function(c,i,j){if(c!=null)if(D.forEach&&c.forEach===D.forEach)c.forEach(i,j);else if(G.isNumber(c.length))for(var l=0,p=c.length;l<p;l++)l in c&&i.call(j,c[l],l,c);else for(l in c)n.hasOwnProperty.call(c,l)&&i.call(j,c[l],l,c)};G.filter=function(c,
i,j){var l=[];if(c!=null)if(S&&c.filter===S)return c.filter(i,j);else G.each(c,function(c,h,n){i.call(j,c,h,n)&&l.push(c)})};G.every=function(c,i,j){if(c==null)return!0;return W&&c.every===W?some.every(i,j):(G.each(c,function(c,h,l){if(!i.call(j,c,h,l))return!1}),!0)};G.some=function(c,i,j){if(c==null)return!1;return T&&c.some===T?c.some(i,j):(G.each(c,function(c,h,l){if(i.call(j,c,h,l))return!0}),!1)};G.map=function(c,i,j){var l=[];if(c!=null)return M&&c.map===M?c.map(i,j):(G.each(c,function(c,h,
n){l.push(i.call(j,c,h,n))}),l)};if(!j.trim)j.trim=function(){var c=/^\s+/,i=/\s+$/;return function(){return this.replace(c,"").replace(i,"")}}();if(!Object.create)Object.create=function(c){function i(){}if(arguments.length>1)throw Error("Object.create implementation only accepts the first parameter.");i.prototype=c;return new i};if(!D.indexOf)D.indexOf=function(c){if(this===void 0||this===null)throw new TypeError;var i=Object(this),j=i.length>>>0;if(j===0)return-1;var l=0;arguments.length>0&&(l=
Number(arguments[1]),isNaN(l)?l=0:l!==0&&l!==1/0&&l!==-(1/0)&&(l=(l>0||-1)*Math.floor(Math.abs(l))));if(l>=j)return-1;for(l=l>=0?l:Math.max(j-Math.abs(l),0);l<j;l++)if(l in i&&i[l]===c)return l;return-1};G.def=function(c,i,j){if(!(c in x||c in C)){var n=j?i:l,j=j||i,i=n;G.isFun(j)?C[c]={name:c,deps:i,wrap:j}:x[c]=j}};G.req=function(c,i){c=G.isArray(c)?c:c.split(" ");if(!i)if(c.length===1)return K(c);else{for(var j=[],l=0,n=c.length;l<n;l++)j.push(K(c[l]));return j}if(c.length===1)J(c[0],function(c){i.call(null,
x[c])});else for(var p=!1,j=function(){if(!p){var j=X(c);j&&(i.apply(null,j),p=!0)}},l=0,n=c.length;l<n;l++)J(c[l],j)};G.format=function(c,i,j){if(!i)return c;if(typeof i!=="object"){var n=j||i;return c.replace(j?i:"{v}",""+n)}else return c.replace(/\{([^{}]+)\}/g,function(c,h){n=i[h];return n!==l?""+n:""})};G.log="undefined"!=typeof console&&"undefined"!=typeof console.log?function(){console.log.apply(console,arguments)}:function(){}})(window,document,void 0);




G.def("gPopMenu",function(){function j(i,j){var p=j?i:i.children(":eq(0)"),q=j?i.next():i.children(":eq(1)");p.mouseover(function(){j?(G.ua.isIE6&&q.width(p.outerWidth()+2),q.show()):i.addClass("hover")});j?q.mouseleave(function(){q.hide()}):i.mouseleave(function(){i.removeClass("hover")})}return function(i,l){$(i).each(function(){j($(this),l)})}});


jQuery.fn.doOnce=function(j,i){var l=this;l.length&&(i?G.req(i,function(){j.apply(l,arguments)}):j.apply(l));return this};


typeof ukey=="undefined"&&(ukey="");

//$(function(){G.req(["gPopMenu"],function(j){j("#mMenu");})});
