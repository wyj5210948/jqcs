/*****************************增值测试地址*************************************/
// var requestUrl = "http://10.5.210.201:8086/mockjs/3/";	//请求后台地址
var requestUrl = "";	//请求后台地址
var currEnv = "php";	//html或者php
var jsonReqHeaderData={}
/*****************************用户测试地址*************************************/
//var requestUrl = "http://10.4.105.172:8051";	//请求后台地址

/*****************************本机测试地址*************************************/
//var requestUrl = "http://localhost:8080";	//请求后台地址


/******************************************************************************
 *                               地址跳转
 *    author:zhongwei by egou
 *    version:1.0
 *    updateTime: 2017-07-28
 *    参数说明:
 *		strUrl:跳转地址   status:错误码，404,500这种, "0000000"-成功
 *      jumpType:0表示本页刷新,1表示打开新页面
 *
 ******************************************************************************/

function jumpUrl(strUrl,status,jumpType,data)
{
    if(typeof(loadingProc) == "function" && !configFlag){
        configFlag = true;
        loadingProc(1);
    }
    if(status != null)
    {
        var objWin = (window.parent.location.href == window.location.href) ? window.location : window.parent.location;
        switch(status)
        {
            case "0000000":
                if(strUrl != "" && strUrl != null)
                    (jumpType == 0) ? window.location.href = strUrl : window.open(strUrl);
                break;
            case "0200000":
                var allPath = escape(document.location.href);
                // clearCookie("sid",jsonReqHeaderData);
                setCookie("urlPath",allPath);
                clearCookie("isLogin");
                objWin.href = window.location.host + "?overtime=1";
                break;
            default:
                if(status != 0000000 && data != null)
                    /*var alertBox=new clsAlertBoxCtrl();
                    alertBox.Alert(data.retDesc,"失败提示");*/
                    alert(data.retDesc);
                break;
        }
    }
}
function comJudgeLogin(){
	$.ajax({ 
	    url: requestUrl+'/vasCommon/getUserInfo', 
	    type:'post',
	    data:'',
	    async:false,
	    success:function (data){
	    	data = JSON.parse(data);

	    	//status==1已登录，0未登录
	    	if(typeof(comJudgeLogin) == 'function'){
	    		comJudgeLoginLogic(data);
	    	}
	    },
	    error:function (data){
	    	alert(data)
	    }
	});
}