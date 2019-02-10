myReady(function(){
    inputs = document.getElementById("form_list").getElementsByTagName("input");
    getcheck = document.getElementById("getCheck");
    checkEmail(inputs[3]);
    checkPassword(inputs[1]);
    getCheckword(getcheck);
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
function checkEmail(email){
    email.onblur = function() {
        var emailFormat = new RegExp("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
        if (!emailFormat.test(email.value) && email.value !== '') {
            email.value = '';
            email.placeholder = "*邮箱格式有误";
        }
        else if (email.value == '') {
            email.placeholder = "*邮箱不能为空";
        }
    }
}
function checkPassword(password){
    password.onblur = function() {
        var code = new RegExp("^(?![0-9]+$)(?![a-zA-Z`~!@#$%^&*()_\\-+=<>?:\"{}|,.\\/;'\\\\[\\]·~！@#￥%……&*（）——\\-+={}|《》？：“”【】、；‘’，。、]+$)[0-9A-Za-z`~!@#$%^&*()_\\-+=<>?:\"{}|,.\\/;'\\\\[\\]·~！@#￥%……&*（）——\\-+={}|《》？：“”【】、；‘’，。、]{6,16}$");
        if (password.value == "") {
            password.value = "";
            password.placeholder = "*密码不能为空";
        }
        else if (!code.test(password.value)) {
            password.value = '';
            password.placeholder = "至少6位数字和其他字符";
        }
    }
}
function getCheckword(getCheck){
    getCheck.onclick = function() {
        var email = document.getElementById("email").value;
        if (email != '') {
            $.ajax({
                url: "http://10.20.0.96:8081/start/sendSMS",
                type: "POST",
                dataType: "json",
                data: {
                    mailbox: registerEmail,
                },
                success: function (data) {
                    var p = JSON.stringify(data);
                    var c = JSON.parse(p);
                    console.log("checksuccess" + c);
                },
                error: function () {
                    console.log("checkfalse");
                },
                xhrFields: {
                    withCredentials: true
                },
                crossDomain: true
            })
        }
    }
}