myReady(function(){
    for(var i=0;i<12;i++){
        var ooo=new Array("pic.jpg","pic1.jpg","pic2.jpg","pic3.jpg","pic4.jpg","pic5.jpg","pic6.jpg","pic7.jpg");
        if(ooo[i]!=undefined) {
            creatProduct(ooo[i]);
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
function creatProduct(kkk){
    var name = "星夜";
    var author = "梵高";
    var status = "竞拍";
    var price = "1000RMB";
    var change = "查看详细信息";
    change.onclick = function (){
        window.location.href = "";
    }
    var warehouse = document.getElementById("warehouse");
    var pictureCol = document.createElement("div");
    pictureCol.setAttribute("class","col-lg-4 col-xs-6");
    var pictureContainer = document.createElement("div");
    var picture = document.createElement("img");
    picture.setAttribute("src",kkk);
    var picture_content = document.createElement("div");
    picture_content.setAttribute("class","pictureInfor");
    picture_content.innerHTML = "<div>名称："+name+"</div><div>作者："+author+"</div><div>状态："+status+"</div><div>价格："+price+"</div><div>"+change+"</div><div>下架</div>";
    warehouse.appendChild(pictureCol);
    pictureCol.appendChild(pictureContainer);
    pictureContainer.appendChild(picture);
    pictureContainer.appendChild(picture_content);
}