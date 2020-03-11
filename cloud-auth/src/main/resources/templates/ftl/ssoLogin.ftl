<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Pro-Cloud微服务平台</title>

<link href="/assets/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/assets/js/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="videozz"></div>
<div class="box">
	<div class="box-a">
    <div class="m-2">
          <div class="m-2-1">
            <form action="/auth/form" method="post" id="loginForm">
                <div class="m-2-2">
                   <input name="username" type="text" placeholder="请输入账号" />
                </div>
                <div class="m-2-2">
                   <input name="password" type="password" placeholder="请输入密码"/>
                </div>
                <div class="m-2-2-1">
                   <input name="imageCode" type="text" placeholder="请输入验证码"/>
                   <img class="image_code" src="/assets/images/1.png"/>
                </div>
                <div class="m-2-2">
                   <button type="submit" value="登录" /> 登录

                </div>

            </form>
          </div>
    </div>
    <div class="m-5">
    <div id="m-5-id-1">
    <div id="m-5-2">
    <div id="m-5-id-2">
    </div>
    <div id="m-5-id-3"></div>
    </div>
    </div>
    </div>
    <div class="m-10"></div>
    <div class="m-xz7"></div>
    <div class="m-xz8 xzleft"></div>
    <div class="m-xz9"></div>
    <div class="m-xz9-1"></div>
    <div class="m-x17 xzleft"></div>
    <div class="m-x18"></div>
    <div class="m-x19 xzleft"></div>
    <div class="m-x20"></div>
    <div class="m-8"></div>
    <div class="m-9"><div class="masked1" id="sx8">Pro-Cloud微服务平台</div></div>
    <div class="m-11">
    	<div class="m-k-1"><div class="t1"></div></div>
        <div class="m-k-2"><div class="t2"></div></div>
        <div class="m-k-3"><div class="t3"></div></div>
        <div class="m-k-4"><div class="t4"></div></div>
        <div class="m-k-5"><div class="t5"></div></div>
        <div class="m-k-6"><div class="t6"></div></div>
        <div class="m-k-7"><div class="t7"></div></div>
    </div>
    <div class="m-14"><div class="ss"></div></div>
    <div class="m-15-a">
    <div class="m-15-k">
    	<div class="m-15xz1">
            <div class="m-15-dd2"></div>
        </div>
    </div>
    </div>
    <div class="m-16"></div>
    <div class="m-17"></div>
    <div class="m-18 xzleft"></div>
    <div class="m-19"></div>
    <div class="m-20 xzleft"></div>
    <div class="m-21"></div>
    <div class="m-22"></div>
    <div class="m-23 xzleft"></div>
    <div class="m-24" id="localtime"></div>
    </div>
</div>
<div style="text-align:center;">
</div>

<script>
    $(function() {
        var randomStr = randomString(10);
        $('.image_code').on('click', function () {
            $.ajax({
                url: '/auth/code/image' //实际使用请改成服务端真实接口
                , type: 'GET'
                , headers: {
                    'deviceId': randomStr
                }
                , success: function (res) {
                    $(".image_code").attr("src", 'data:image/*;base64,' + res.data);
                }
            });
        });

        $('.image_code').click();

        function randomString(n) {
            var str = "abcdefghijklmnopgrstuvwxyz9876543210";
            var len = str.length;
            var tmp = "";
            for (var i = 0; i < n; i++) {
                tmp += str.charAt(Math.floor(Math.random() * len))
            }
            return tmp;
        }
    })
</script>
</body>
</html>
