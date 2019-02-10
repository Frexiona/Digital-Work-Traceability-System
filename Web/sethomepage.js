var pictureObject = new Array();
pictureObject[0] = {
    name:"xingye",
    picture:"pic.jpg",
    author:"fangao",
    price:"111RMB",
    hot:111,
    Id:''
}
myReady(function(){
    getpicture();
   sort();
   init();
   classify();
   change_page();
   document.getElementById("search").onclick = function () {
       var pic = document.getElementById("search_content").value;
       if(pic!=""){
           window.location.href = "search.html";
       }
       // $.ajax({
       //     data: pic,
       //     dataType :"json",
       //     type :"POST",
       //     url :,
       //     success:function(data){
       //
       //     },
       //     error:function () {
       //
       //     }
       // })
   }
})
function change_page(){
    document.getElementById("last").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("last");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    document.getElementById("next").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
}
function init(){
   // pictureObject = getpicture("init");
    for(var i=0;i<24;i++){
        //请求发送
        if(pictureObject[i]!=undefined) {
            creatPicture(pictureObject[i]);
        }
    }
}
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
function sort() {
    status = 1 ;
    var sort_all = document.getElementById("sort_all");
    var sort_time = document.getElementById("sort_time");
    var sort_hot = document.getElementById("sort_hot");
    var sort_price = document.getElementById("sort_price");
    sort = new Array(sort_all,sort_time,sort_price,sort_hot);
    sort_all.onclick = function () {
       for(var i = 0 ;i < 4 ; i++ ){
           if(i == 0){
               sort[i].innerHTML = "综合排序";
               sort[i].style.flex = 2;
               sort[i].style.backgroundColor = "white";
           }
           else {
               sort[i].style.flex = 1;
               sort[i].style.backgroundColor = "whitesmoke";
               if (sort[i].innerHTML.length > 2) {
                   sort[i].innerHTML = sort[i].innerHTML.slice(0, 2);
               }
           }
        }
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    sort_hot.onclick = function () {
        for(var i = 0 ;i < 4 ; i++ ){
            if(i == 3){
                if(status == 1) {
                    sort[i].innerHTML = "热度高到低";
                    status--;
                }
                else {
                    sort[i].innerHTML = "热度低到高";
                    status++;
                }
                sort[i].style.flex = 2;
                sort[i].style.backgroundColor = "white";
            }
            else {
                sort[i].style.flex = 1;
                sort[i].style.backgroundColor = "whitesmoke";
                if (sort[i].innerHTML.length > 2) {
                    sort[i].innerHTML = sort[i].innerHTML.slice(0, 2);
                }
            }
        }
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    sort_time.onclick = function () {
        for(var i = 0 ;i < 4 ; i++ ){
            if(i == 1){
                if(status == 1) {
                    sort[i].innerHTML = "时间高到低";
                    status--;
                }
                else {
                    sort[i].innerHTML = "时间低到高";
                    status++;
                }
                sort[i].style.flex = 2;
                sort[i].style.backgroundColor = "white";
            }
            else {
                sort[i].style.flex = 1;
                sort[i].style.backgroundColor = "whitesmoke";
                if (sort[i].innerHTML.length > 2) {
                    sort[i].innerHTML = sort[i].innerHTML.slice(0, 2);
                }
            }
        }
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    sort_price.onclick = function () {
        for (var i = 0; i < 4; i++) {
            if (i == 2) {
                if (status == 1) {
                    sort[i].innerHTML = "价格高到低";
                    status--;
                }
                else {
                    sort[i].innerHTML = "价格低到高";
                    status++;
                }
                sort[i].style.flex = 2;
                sort[i].style.backgroundColor = "white";
            }
            else {
                sort[i].style.flex = 1;
                sort[i].style.backgroundColor = "whitesmoke";
                if (sort[i].innerHTML.length > 2) {
                    sort[i].innerHTML = sort[i].innerHTML.slice(0, 2);
                }
            }
        }
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for(var i = 0; i<12;i++){
            //请求发送
            if(pictureObject[i]!=undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
}
function creatPicture(pic){
    /*products*/
    var picture_container = document.createElement("div");
    picture_container.setAttribute("class","col-md-3 col-xs-6");
    /*col*/
    var pictureCol = document.createElement("div");
    pictureCol.onclick = function () {
        //跳转至购买页面
        // $.ajax({
        //     url:"",
        //     data:"",
        //     dataType:"",
        //     type:"",
        //     success:function (data) {
        //
        //     },
        //     error:function () {
        //
        //     }
        // })
        console.log(this);
        window.location.href = "onSale.html";
    }
    /*img*/
    var picture = document.createElement("img");
    picture.setAttribute("src",pic.picture);
    /*name,author*/
    var picture_infor = document.createElement("span");
    var picture_name = document.createElement("div");
    picture_name.innerHTML = "作品名称:"+pic.name;
    var picture_author =document.createElement("div");
    picture_author.innerHTML = "作者:"+pic.author;
    /*price,owner,status,time*/
    var picture_content = document.createElement("div");
    var picture_price = document.createElement("div");
    picture_price.innerHTML = "ID："+pic.Id;
    var picture_owner = document.createElement("div");
    picture_owner.innerHTML = "价格："+pic.price;
    var picture_status = document.createElement("div");
    picture_status.innerHTML = "状态："+pic.status;
    var picture_hot = document.createElement("div");
    picture_hot.innerHTML = "赞"+pic.hot;
    picture_hot.onclick = function (e) {
        var number = parseInt(pic.hot)+1;
        this.innerHTML = "已赞"+number;
        e.stopPropagation();
        // $.ajax({
        //     url:"",
        //     data:"",
        //     dataType:"",
        //     type:"",
        //     success:function(data){
        //
        //     },
        //     error:function () {
        //
        //     }
        // })
    }

    document.getElementById("picture_list").appendChild(picture_container);//div
    picture_container.appendChild(pictureCol);//div
    pictureCol.appendChild(picture);//img
    pictureCol.appendChild(picture_content);//div
    picture_content.appendChild(picture_infor);
    picture_infor.appendChild(picture_name);
    picture_infor.appendChild(picture_author);
    picture_content.appendChild(picture_price);
    picture_content.appendChild(picture_owner);
    picture_content.appendChild(picture_status);
    picture_content.appendChild(picture_hot);
}
function classify(){
    document.getElementById("all").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for (var i = 0; i < 12; i++) {
            //请求发送
            if (pictureObject[i] != undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    document.getElementById("onsale").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for (var i = 0; i < 12; i++) {
            //请求发送
            if (pictureObject[i] != undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    document.getElementById("auction").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for (var i = 0; i < 12; i++) {
            //请求发送
            if (pictureObject[i] != undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
    document.getElementById("notsale").onclick = function () {
        var picture_list = document.getElementById("picture_list");
        picture_list.innerHTML = "";
        //getpicture("next");
        for (var i = 0; i < 12; i++) {
            //请求发送
            if (pictureObject[i] != undefined) {
                creatPicture(pictureObject[i]);
            }
        }
    }
}
function getpicture(){
    $.ajax({
        url:"http://127.0.0.1:8080/allImg",
        async: false,
        type: "POST",
        contentType:false,
        processData:false,
        cache:false,
        success:function(data){
            dataString = JSON.stringify(data);
            dataObject = JSON.parse(dataString);
            for(var i =0;i<dataObject.data.length;i++) {
                pictureObject[i] = dataObject.data[i];
            }
        },
        error:function(){
            console.log("失败");
        }
    })
}