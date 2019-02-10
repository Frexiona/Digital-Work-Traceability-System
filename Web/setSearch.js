myReady(function(){
    var pictureObject = new Array();
    pictureObject[0] = {
        picture: "pic.jpg",
        name : "星夜",
        author : "梵高",
        price : "1000RMB",
        owner : "pyy",
        status : "立即出售",
        time : "1997.06.09"
    }
    pictureObject[1]=pictureObject[0];
    // $.ajax({
    //     data:,
    //     url:,
    //     dataType:"json",
    //     type:"POST",
    //     success:function(data){
    //
    //     },
    //     error:function () {
    //
    //     }
    // })
    for(var i=0;i<pictureObject.length;i++) {
        if(pictureObject[i]!=undefined) {
            document.getElementById("result").innerHTML +=
                "<div>\n" +
                "            <div>\n" +
                "                <img src='" + pictureObject[i].picture + "'>\n" +
                "            </div>\n" +
                "            <div>\n" +
                "                <div>\n" +
                "                    <div>作品名称：" + pictureObject[i].name + "</div>\n" +
                "                    <div>作者：" + pictureObject[i].author + "</div>\n" +
                "                    <div>作品完成日期:" + pictureObject[i].time + "</div>\n" +
                "                    <div>拥有者地址：223cefe5c5ld3vlfvs5dlwvf5e6vl</div>\n" +
                "                    <div>出售价格：" + pictureObject[i].price + "</div>\n" +
                "                    <div>状态:" + pictureObject[i].status + "</div>\n" +
                "                    <div><button type=\"button\">购买</button></div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>";
        }
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