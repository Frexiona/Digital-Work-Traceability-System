myReady(function () {
    document.getElementById("turn").onclick = function () {
        window.location.href = "change_product.html"
    }
})
function myReady(fn){
    //对于现代浏览器
    if(document.addEventListener){
        document.addEventListener("DOMContentLoaded",fn,false);
    }else{
        IEContentLoaded(fn);
    }
    //IE模拟
    function IEContentLoaded(fn){
        var done = false;
        var init = function(){
            if(!done){
                done = ture ;
                fn();
            }
        };
        (function(){
            try{
                //dom树为创建钱出错
                d.documentElement.doScroll('left');
            }catch(e){
                setTimeout(arguments.callee,50);
                return;
            }
            init();
        });
        //监听document的加载状态
        d.onreadystatechange = function(){
            if(d.readyState == 'complete'){
                d.onreadystatechange = null;
                init();
            }
        }
    }
}