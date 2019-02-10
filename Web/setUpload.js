myReady(function(){
    upload();
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
function Goods_shelves(){
    var Pic = document.getElementById("picUpload").value;
    var name = document.getElementById("name").value;
    var author = document.getElementById("author").value;
    var price = document.getElementById("price").value;
    var infor = document.getElementById("infor").value;
    var select = document.getElementById("select_list").value;
    if(Pic!=""&&name!=""&&author!=""&&price!=""&&infor!=""&&select!=""){
        $.ajax({
            url: "",
            data:{
                Pic : Pic,
                name : name,
                author :author,
                price :price,
                infor : infor,
                select :select
            },
            type :"POST",
            dataType :"json",
            success:function (data) {

            },
            error:function (request) {

            }
        })
    }
}
function previewImage(file)
{
    var MAXWIDTH  = 100;
    var MAXHEIGHT = 100;
    var div = document.getElementById('preview');
    if(file.files[0].type =="image/jpeg"||file.files[0].type=="image/png"||file.files[0].type=="image/jpg") {
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.onload = function () {
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width = rect.width;
                img.height = rect.height;
            }
            var reader = new FileReader();
            reader.onload = function (evt) {
                img.src = evt.target.result;
                console.log(evt);
            }
            reader.readAsDataURL(file.files[0]);
        }
        else {
            var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        }
    }
    else {
        file.value = "";
        alert("请传入图片，格式为png/jpg");
    }
}
function clacImgZoomParam( maxWidth, maxHeight, width, height ){
    var param = {top:0, left:0, width:width, height:height};
    if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
        if( rateWidth > rateHeight )
        {
            param.width =  maxWidth;
            param.height = Math.round(height / rateWidth);
        }else
        {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}
function upload() {
    document.getElementById("submit").onclick = function (e) {
        e.preventDefault();
        var picture = {
            content:'',
            name:'',
            author:'',
            price:'',
            infor:'',
            status:''
        }
        var inputs = document.getElementById("form").getElementsByTagName("input");
        picture.name = inputs[1].value;
        picture.author = inputs[2].value;
        picture.price = inputs[3].value;
        picture.infor = inputs[4].value;
        s= document.getElementById("form").getElementsByTagName("select");
        picture.status = s[0].value;
        if(picture.name!=''&&picture.author!=''&&picture.infor!=''&&picture.price!="") {
            var choose = confirm("是否提交");
            if(choose==true) {
                var pic = $("#file")[0].files[0];
                var data = new FormData();
                data.append("file",pic);
                data.append("name",picture.name);
                data.append("author",picture.author);
                data.append("price",picture.price);
                data.append("info",picture.infor);
                data.append("status",picture.status);
                $.ajax({
                    url: "http://127.0.0.1:8080/upload",
                    data: data,
                    async: false,
                    type: "POST",
                    contentType:false,
                    processData:false,
                    cache:false,
                    success: function (data) {
                        var dataString = JSON.stringify(data);
                        var dataObject = JSON.parse(dataString);
                        console.log("success"+dataString);
                        //window.location.href = "";
                    },
                    error: function (data) {
                        var dataString = JSON.stringify(data);
                        var dataObject = JSON.parse(dataString);
                        console.log("success"+dataString);
                    }
                })
            }
        }
    }
}