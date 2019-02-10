myReady(function(){
    PictureAndInformation();
    HistoryAndView();
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
function HistoryAndView(){
    var history_list = "false" ;
    var view_list = "false" ;
    document.getElementById("DisplayHistory").onclick = function () {
        var history = document.getElementById("history");
        if(history_list=="false") {
            this.innerText = "折叠历史信息"
            history.style.display = "block";
            history_list = "ture";
        }
        else if(history_list == "ture"){
            this.innerText = "显示历史信息"
            history.style.display = "none";
            history_list = "false";
        }
    }
    document.getElementById("DisplayView").onclick = function () {
        var view = document.getElementById("view");
        if(view_list=="false") {
            this.innerText = "折叠相关评论"
            view.style.display = "block";
            view_list = "ture";
        }
        else if(view_list == "ture"){
            this.innerText = "显示相关评论"
            view.style.display = "none";
            view_list = "false";
        }
    }
}
function PictureAndInformation(){
    var pictureInfor = document.getElementById("infor");
    var picture = document.getElementById("picture");
    pictureObject = {
        picture: '',
        name: '',
        author: '',
        time: '',
        owner: '',
        price: '',
        size: '',
        status: '',
        history: '',
        view: ''
    }
    // $.ajax({
    //     data:{
    //
    //     },
    //     dataType:"json",
    //     type:'POST',
    //     url: '',
    //     success:function(data){
    //
    //     },
    //     error:function(){
    //
    //     }
    // })
    picture.src = pictureObject.picture;
    pictureInfor.innerHTML = " <div>作品名称："+pictureObject.name+"</div>\n" +
        "                    <div>作者: "+pictureObject.author+"</div>\n" +
        "                    <div>竞拍截止："+pictureObject.time+"</div>\n" +
        "                    <div>最高竞价："+pictureObject.price+"</div>\n" +
        "                    <div>尺寸："+pictureObject.size+"</div>\n" +
        "                    <div>状态："+pictureObject.status+"</div>\n" +
        "                    <div><input type=\"number\" placeholder=\"输入竞拍金额\"><br/><button>竞拍</button></div>"
}