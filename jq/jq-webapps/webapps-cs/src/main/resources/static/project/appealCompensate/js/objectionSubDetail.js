function clsMethodLee(){
    this.requestUrl = {
        "path1":"/product/category/down/list",//异议产品下拉接口（产品大类接口）
        "path2":"/orderUnit/findByPage",//订货单位列表
        "path3":"/unitOfUse/findByPage",//使用单位列表
        "path4":"/objectionTiBao/findDetails",//异议提报新建/修改/详情/销售审核数据回显 1是新建2是修改3是详情4销售审核
        "path5":"/millsheet/findIsTrue",//质证书编号检验
        "path6":"/objectionTiBao/update",//异议提报新增/修改/保存/销售审核（保存、驳回、通过）1审核保存操作2驳回操作3通过操作4修改保存5新增保存
        "path7":"/objectionTiBao/down",//详情页面下载功能
        "path8":"/objectionTiBao/printing",//详情页面打印功能
        "path9":"/objectionDiaoCha/findDetail",//异议调查外部调查内部调查回显数据|确认书审核   1是外部调查 2是内部调查 3确认书审核
        "path10":"/objectionDiaoCha/update",//内外部调查报告（保存，跟踪，提交）异议处理确认书（通过 ，驳回）
                                            //1外部保存2外部跟踪3外部提交4内部保存5内部提交6确认书通过7确认书审核
        "path11":"/file/upload"//上传
    };
    this.documentLee = null;
    this.htmlType = GetQueryString("htmlType");//判断页面类型0——新建 5修改 1——详情  2——销售审核  3——外部调查  4——内部调查 6-确认书审核
    this.claimNo = GetQueryString("claimNo") == null ? "":GetQueryString("claimNo");//异议编号
    this.filePath = [];
    this.init = clsMethodLee$init;//初始化页面的展示内容,绑定dom节点
    this.parse = clsMethodLee$parse;//初始化页面的数据
    this.operate = clsMethodLee$operate;//初始化页面的数据
    this.refresh = clsMethodLee$refresh;//刷新页面的数据
}

function clsMethodLee$init(){
    //订货单位添加
    this.dinghuoCreate = $("#dinghuoCreate");
    //使用单位添加
    this.shiyongCreate = $("#shiyongCreate");
    //质证书编号
    this.millSheetNo = $("#millSheetNo");
    //新建|修改  页面清空按钮
    this.firstReset = $("#firstReset");
    //新建|修改  页面保存按钮
    this.firstSave = $("#firstSave");
    //详情页面打印按钮
    this.secondPrint = $("#secondPrint");
    //详情页面下载按钮
    this.secondDownload = $("#secondDownload");
    //销售管理保存按钮
    this.thirdSave = $("#thirdSave");
    //销售管理通过按钮
    this.thirdPromise = $("#thirdPromise");
    //销售管理驳回按钮
    this.thirdReject = $("#thirdReject");
    //外部调查保存按钮
    this.forthsubmit = $("#forthsubmit");
    //外部调查跟踪按钮
    this.forthFoolow = $("#forthFoolow");
    //外部调查提交按钮
    this.forthSave = $("#forthSave");
    //内部调查保存按钮
    this.fifthSave = $("#fifthSave");
    //内部调查提交按钮
    this.fifthSubmit = $("#fifthSubmit");
    //保证书审核通过按钮
    this.sixthPromise = $("#sixthPromise");
    //保证书审核驳回按钮
    this.sixthReject = $("#sixthReject");

    this.parse();

}
function clsMethodLee$parse(){
    //判断页面类型（进行显示隐藏dom节点）0——新建 5修改 1——详情  2——销售审核  3——外部调查  4——内部调查
    switch (Number(this.htmlType)){
        case 0://新建
            $(".box0").show();
            $(".box02").show();
            getAjaxResult(document.body.jsLee.requestUrl.path4,"POST",{"claimNo":this.claimNo,"optionType":1},"htmlInit(data)");//数据回显操作
            break;
        case 5://修改
            $(".box0").show();
            $(".box02").show();
            getAjaxResult(document.body.jsLee.requestUrl.path4,"POST",{"claimNo":this.claimNo,"optionType":2},"htmlInit(data)");//数据回显操作
            break;
        case 1://详情
            $(".box1").show();
            $(".box12").show();
            $("#productId").remove();//删除第一个异议产品。（原新建页面的异议产品input）
            $("#filePathTemplate").parent().remove();//删除第一个上传按钮box
            getAjaxResult(document.body.jsLee.requestUrl.path4,"POST",{"claimNo":this.claimNo,"optionType":3},"htmlInit(data)");//数据回显操作
            break;
        case 2://销售审核
            $(".box12").show();
            $(".box02").show();
            $(".box2").show();
            $("#productId").remove();//删除第一个异议产品。（原新建页面的异议产品input）
            $("#millSheetNo").remove();//删除第一个质证书。（原新建页面的质证书input）
            getAjaxResult(document.body.jsLee.requestUrl.path4,"POST",{"claimNo":this.claimNo,"optionType":4},"htmlInit(data)");//数据回显操作
            break;
        case 3://外部调查
            $("#boxFirst").remove();
            $("#boxSecond").show();
            $(".box3").show();
            $("#productId").remove();//删除第一个异议产品。（原新建页面的异议产品input）
            $("#millSheetNo").remove();//删除第一个质证书。（原新建页面的质证书input）
            var ue = UE.getEditor('editor');
            var ue2 = UE.getEditor('editor2');
            getAjaxResult(document.body.jsLee.requestUrl.path9,"POST",{"claimNo":this.claimNo,"optionType":1},"htmlInit2(data)");//数据回显操作
            break;
        case 4://内部调查
            $("#boxFirst").remove();
            $("#boxSecond").show();
            $(".box4").show();
            $("#inOutReomve").remove();
            $("#productId").remove();//删除第一个异议产品。（原新建页面的异议产品input）
            $("#millSheetNo").remove();//删除第一个质证书。（原新建页面的质证书input）
            $("#editor").remove();
            $("#editor2").remove();
            var ue = UE.getEditor('editor');
            var ue2 = UE.getEditor('editor2');
            getAjaxResult(document.body.jsLee.requestUrl.path9,"POST",{"claimNo":this.claimNo,"optionType":2},"htmlInit2(data)");//数据回显操作
            break;
        case 6://确认书审核
            $("#boxFirst").remove();
            $("#boxSecond").show();
            $(".box5").show();
            $("#productId").remove();//删除第一个异议产品。（原新建页面的异议产品input）
            $("#millSheetNo").remove();//删除第一个质证书。（原新建页面的质证书input）
            $("#rejectReason").remove();//删除第一个驳回原因input
            getAjaxResult(document.body.jsLee.requestUrl.path9,"POST",{"claimNo":this.claimNo,"optionType":3},"htmlInit2(data)");//数据回显操作
            break;
    }
    initValidate($("#submitBox")[0]);
    $("#claimNo").val(document.body.jsLee.claimNo);
    this.operate();
}

function clsMethodLee$operate(){
    this.dinghuoCreate.on("click",function () {//订货单位添加操作
        openWin('650', '500', 'listPopup', true);
        $("#dinghuoListPopup").show();
        $("#shiyongListPopup").hide();
        $("#listPopupTitle").html("订货单位列表");
        initplugPath($("#dinghuoListPopupBox")[0],"standardTableCtrl",document.body.jsLee.requestUrl.path2,null,"POST");
    });
    this.shiyongCreate.on("click",function () {//使用单位添加操作
        openWin('650', '500', 'listPopup', true);
        $("#dinghuoListPopup").hide();
        $("#shiyongListPopup").show();
        $("#listPopupTitle").html("使用单位列表");
        initplugPath($("#shiyongListPopupBox")[0],"standardTableCtrl",document.body.jsLee.requestUrl.path3,null,"POST");

    });
    //质证书编号改变，进行接口后台判断
    this.millSheetNo.on("change",function () {
        getAjaxResult(document.body.jsLee.requestUrl.path5,"POST",{"millSheetNo":$(this).val()},"millSheetNoCheckCallBack(data)");
    });
    
    //新建 || 修改页面清空操作
    this.firstReset.on("click",function () {
        $("#submitBox input[type=text]").val("");
        $("#submitBox textarea").val("");$("#productId").attr("initValue","");
        initplugPath($("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");

    });
    //新建 || 修改页面保存操作
    this.firstSave.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            if(document.body.jsLee.htmlType == 0){//新建
                jsonParam.optionStuts = 5;
            }else if(document.body.jsLee.htmlType == 5){//修改
                jsonParam.optionStuts = 4;
            }
            getAjaxResult(document.body.jsLee.requestUrl.path6,"POSt",jsonParam,"firstSaveCallBack(data)")
        }else{

        }
    });

    //详情页面打印操作
    this.secondPrint.on("click",function(){

    });
    //详情页面下载操作
    this.secondDownload.on("click",function(){
        var importParam = "name=" + JSON.stringify(document.body.jsLee.claimNo);
        $.download(requestUrl + document.body.jsLee.requestUrl.path7, importParam, "POST");
    });
    //销售管理保存操作
    this.thirdSave.on("click",function(){
        $("#rejectReason").removeClass("required");
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 1;
            getAjaxResult(document.body.jsLee.requestUrl.path6,"POSt",jsonParam,"firstSaveCallBack(data)")
        }
    });
    //销售管理通过操作
    this.thirdPromise.on("click",function(){
        $("#rejectReason").removeClass("required");
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 3;
            getAjaxResult(document.body.jsLee.requestUrl.path6,"POSt",jsonParam,"firstSaveCallBack(data)")

        }
    });
    //销售管理驳回操作
    this.thirdReject.on("click",function(){
        $("#rejectReason").addClass("required");
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 2;
            getAjaxResult(document.body.jsLee.requestUrl.path6,"POSt",jsonParam,"firstSaveCallBack(data)")
        }
    });

    //外部调查保存按钮
    this.forthsubmit.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 1;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });
    //外部调查跟踪按钮
    this.forthFoolow.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 2;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });
    //外部调查提交按钮
    this.forthSave.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 3;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });
    //内部调查保存按钮
    this.fifthSave.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 4;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });
    //内部调查提交按钮
    this.fifthSubmit.on("click",function () {
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 5;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });
    //通知书审核通过操作
    this.sixthPromise.on("click",function(){
        $("#rejectReason").removeClass("required");
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 6;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")

        }
    });
    //通知书审核驳回操作
    this.sixthReject.on("click",function(){
        $("#rejectReason").addClass("required");
        if(boxChecked()){
            var jsonParam = paramJson();
            jsonParam.optionStuts = 7;
            getAjaxResult(document.body.jsLee.requestUrl.path10,"POSt",jsonParam,"secondSaveCallBack(data)")
        }
    });

}
function clsMethodLee$refresh(){

}

//插件渲染操作
function clsStandardTableCtrl$progress(jsonItem, cloneRow) {
    if (this.ctrl.id == "dinghuoListPopupBox") {//订货单位列表
        $(cloneRow).find("#createWay").on("click",function () {
            closePopupWin();
            setValue4Desc(jsonItem,$("#firstDetail")[0])//赋值
        });
    }else if (this.ctrl.id == "shiyongListPopupBox"){//使用单位列表
        $(cloneRow).find("#createWay").on("click",function () {
            closePopupWin();
            setValue4Desc(jsonItem,$("#secondDetail")[0])//赋值
        });
    }
}

//按照组件重新编写div校验
function showErrInfoByCustomDiv(elem,error)
{
    $(elem).poshytip({
        className: 'tip-twitter',
        showOn: 'none',
        alignTo: 'target',
        alignX: 'right',
        alignY: 'inner-right',
        content:error,
        offsetX: 5,
        offsetY: -30,
        autoHide:true,
        hideTimeout:5000
    });
    $(elem).poshytip('hide');
    $(elem).poshytip('show');
}

//已有数组，初始化插件;
function initplugData(docm,comType,data){
    $(docm).attr("comType",comType);
    docm.data = {"rspBody":{"resultData":data}};
    document.body.jsCtrl.ctrl = docm;
    document.body.jsCtrl.init();
}
//未知数组，已有接口，初始化插件;
function initplugPath(docm,comType,reqPath,reqParam,reqMethod){
    if(reqPath != null){
        $(docm).attr("reqPath",reqPath);
    }
    if(reqParam != null){
        $(docm).attr("reqParam",JSON.stringify(reqParam));
    }
    if(reqMethod != null){
        $(docm).attr("reqMethod",reqMethod);
    }
    $(docm).attr("comType",comType);
    document.body.jsCtrl.ctrl = docm;
    document.body.jsCtrl.init();
}

function htmlInit(data){//数据回显回调
    data = JSON.parse(data);
    if(data.retCode == "0000000"){
        setValue4Desc(data.rspBody,$("#submitBox")[0])//赋值
        if(document.body.jsLee.htmlType == 0 || document.body.jsLee.htmlType == 5){//新建||修改
            //异议产品赋值
            $("#productId").attr("initValue",data.rspBody.productId);
            initplugPath( $("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");
            //回显上传图片地址
            if(data.rspBody.filePath == null || data.rspBody.filePath == ""){
                document.body.jsLee.filePath = [];
            }else{
                document.body.jsLee.filePath = data.rspBody.filePath.split(";");
            }

            filePathShow(document.body.jsLee.filePath);


        }else if(document.body.jsLee.htmlType == 1){//详情
            //异议产品赋值
            $("#productId").attr("initValue",data.rspBody.productId).attr("disabled",true);
            initplugPath( $("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");
            //置灰
            $("#submitBox input[type=text]").attr("disabled",true).addClass("changeGary");
            $("#submitBox textarea").attr("disabled",true).addClass("changeGary");
            //异议单位赋值
            $("#dissentingUnitA").find("input").attr({"checked":false,"disabled":true}).addClass("changeGary");
            $("#dissentingUnitA").find("input[value=" + data.rspBody.dissentingUnit + "]").attr("checked",true);
            //回显上传图片地址
            if(data.rspBody.filePath == null || data.rspBody.filePath == ""){
                document.body.jsLee.filePath = [];
            }else{
                document.body.jsLee.filePath = data.rspBody.filePath.split(";");
            }
            filePathShow(document.body.jsLee.filePath);
        }else if(document.body.jsLee.htmlType == 2){//销售审核页面
            //异议产品赋值
            $("#productId").attr("initValue",data.rspBody.productId);
            initplugPath( $("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");
            //异议单位赋值
            $("#dissentingUnitA").find("input").attr("checked",false);
            $("#dissentingUnitA").find("input[value=" + data.rspBody.dissentingUnit + "]").attr("checked",true);
            //异议类别赋值
            $("#productIdA").find("input").attr("checked",false);
            $("#productIdA").find("input[value=" + data.rspBody.productId + "]").attr("checked",true);
            $(".disNone").show().parent().next().addClass("required");
            //回显上传图片地址
            if(data.rspBody.filePath == null || data.rspBody.filePath == ""){
                document.body.jsLee.filePath = [];
            }else{
                document.body.jsLee.filePath = data.rspBody.filePath.split(";");
            }
            filePathShow(document.body.jsLee.filePath);
            $("#filePathTemplate").parent().css("width","590px");
        }
    }
}

function htmlInit2(data){//数据回显回调
    data = JSON.parse(data);
    if(data.retCode == "0000000"){
        setValue4Desc(data.rspBody,$("#submitBox")[0])//赋值
        if(document.body.jsLee.htmlType ==3 || document.body.jsLee.htmlType == 4){//外部调查|内部调查|
            //异议产品赋值
            $("#productId").attr("initValue",data.rspBody.productId).attr("disabled",true);
            initplugPath( $("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");
            //输入框置灰
            $("#submitBox input[type=text]").attr("disabled",true).addClass("changeGary");
            if(document.body.jsLee.htmlType == 3){
                //使用量手填  缺陷名称  生产日期 班时班次
                $("#amountOfUse").removeAttr("disabled").removeClass("changeGary");
                $("#defectName").removeAttr("disabled").removeClass("changeGary");
                $("#productDt").removeAttr("disabled").removeClass("changeGary");
                $("#shift").removeAttr("disabled").removeClass("changeGary");
                //富文本数据回显
                /*UE.getEditor('editor').setContent(data.rspBody.inquireInfo);*/
                var ue = UE.getEditor('editor');
                ue.ready(function() {//编辑器初始化完成再赋值
                    data.rspBody.inquireInfo = '<p>123123<img style="max-width: 400px; width: 220px; height: 145px;" src="http://192.168.1.115:20183/res/2018/08/jpg/20180830105725_8759.jpg" title="abc.jpg" alt="abc.jpg" width="220" height="145"/></p>';
                    ue.setContent(data.rspBody.inquireInfo);  //赋值给UEditor
                });
                var ue2 = UE.getEditor('editor2');
                ue2.ready(function() {//编辑器初始化完成再赋值
                    data.rspBody.fieldConclusion = '<p>123123<img style="max-width: 400px; width: 220px; height: 145px;" src="http://192.168.1.115:20183/res/2018/08/jpg/20180830105725_8759.jpg" title="abc.jpg" alt="abc.jpg" width="220" height="145"/></p>';
                    ue2.setContent(data.rspBody.fieldConclusion);  //赋值给UEditor
                });
            }else {
                //富文本数据回显
                var ue = UE.getEditor('editor');
                ue.ready(function() {//编辑器初始化完成再赋值
                    data.rspBody.inquireInfo = '<p>123123<img style="max-width: 400px; width: 220px; height: 145px;" src="http://192.168.1.115:20183/res/2018/08/jpg/20180830105725_8759.jpg" title="abc.jpg" alt="abc.jpg" width="220" height="145"/></p>';
                    ue.setContent(data.rspBody.inquireInfo);  //赋值给UEditor
                });
                var ue2 = UE.getEditor('editor2');
                ue2.ready(function() {//编辑器初始化完成再赋值
                    data.rspBody.fieldConclusion = '<p>123123<img style="max-width: 400px; width: 220px; height: 145px;" src="http://192.168.1.115:20183/res/2018/08/jpg/20180830105725_8759.jpg" title="abc.jpg" alt="abc.jpg" width="220" height="145"/></p>';
                    ue2.setContent(data.rspBody.fieldConclusion);  //赋值给UEditor
                });
                /*UE.getEditor('editor').setContent(data.rspBody.claimDesc);
                UE.getEditor('editor2').setContent(data.rspBody.inquireInfo);*/
            }

        }else if(document.body.jsLee.htmlType == 6){//证明书审核
            $("#productId").attr("initValue",data.rspBody.productId).attr("disabled",true);
            initplugPath( $("#productId")[0],"singleSelectCtrl",document.body.jsLee.requestUrl.path1,null,"POST");
            //置灰
            $("#submitBox input[type=text]").attr("disabled",true).addClass("changeGary");
            $("#submitBox textarea").attr("disabled",true).addClass("changeGary");
            $("#rejectReason").removeClass("changeGary").removeAttr("disabled");
        }
    }
}

//质证书编号校验回调
function millSheetNoCheckCallBack(data){
    data = JSON.parse(data);
    if(data.retCode == "0000000"){

    }else{
        alert(data.retDesc);
        $("#millSheetNo").val("");
    }
}

function boxChecked(){
    initValidate($("#submitBox")[0]);
    var valiClass=new clsValidateCtrl();
    if(!valiClass.validateAll4Ctrl($("#submitBox")[0]) || !$("#productId option:selected").val()){
        if(!$("#productId option:selected").val()){
            showErrInfoByCustomDiv($("#productId").next()[0],"请选择异议产品")
        }
        return false;
    }
    return true;
}

//完成提交参数拼接
function paramJson(){
    var jsonParam = {"claimNo":"","productId":"","millSheetNo":"","customerId":"","customerName":"","custAddr":"","custEmpNo":"","custTel":"","lastUserId":"","lastUser":"","lastUserAddr":"","createEmpNo":"","lastUserTel":"","battenPlateNo":"","designation":"","used":"","contractNo":"","contractVolume":"","specs":"","originalWeight":"","orderNo":"","originalCarNo":"","contractUnitPrice":"","objectionNum":"","endProcessingTech":"","claimDesc":"","claimReason":"","rejectReason":"","productDt":"","shift":"","userRequirement":"","handingSuggestion":"","inquireInfo":"","fieldConclusion":"","claimVerdict":"","improvement":""};
    getValue4Desc(jsonParam,$("#submitBox")[0]);
    //产品类别
    jsonParam.productId = $("#productId option:selected").val();
    jsonParam.productName = $("#productId option:selected").html();
    //异议单位
    jsonParam.dissentingUnit = $("#dissentingUnitA input:checked").val();
    if(document.body.jsLee.htmlType == 3){//销售审核
        //异议类别
        jsonParam.claimType = $("#claimTypeA input:checked").val();
    }
    //上传图片
    var strFilePath = "";
    for(var nI = 0 ; nI < document.body.jsLee.filePath.length; nI++ ){
        if(nI == document.body.jsLee.filePath.length - 1){
            strFilePath += document.body.jsLee.filePath[nI];
        }else{
            strFilePath += document.body.jsLee.filePath[nI] + ";";
        }
    }
    jsonParam.filePath = strFilePath;
    //富文本编辑器
    if(document.body.jsLee.htmlType == 3){//外部调查
        jsonParam.inquireInfo = UE.getEditor('editor').getContent();
        jsonParam.fieldConclusion = UE.getEditor('editor2').getContent();
    }else if(document.body.jsLee.htmlType == 4){//内部调查
        jsonParam.claimDesc = UE.getEditor('editor').getContent();
        jsonParam.inquireInfo = UE.getEditor('editor2').getContent();

    }
    return jsonParam;
}

function firstSaveCallBack(data){
    data = JSON.parse(data);
    if(data.retCode == "0000000"){
        var alertBox=new clsAlertBoxCtrl();
        alertBox.Alert(data.retDesc,"成功提示",1,"","successJump");
    }
}

function secondSaveCallBack(data){
    data = JSON.parse(data);
    if(data.retCode == "0000000"){
        jumpUrl("objectionReasearch.html","0000000",0);
    }
}
function clsUploadCtrl$successAfter(ctrl, response)
{
    document.body.jsLee.filePath.push(response.rspBody.viewUrl);
    filePathShow(document.body.jsLee.filePath);
}

//回显上传图片地址
function filePathShow(arr){
    $("*[id=filePathClone]").remove();
    for(var nI = 0; nI < arr.length; nI++ ){
        var filePathClone = $("#filePathTemplate").clone(true);
        filePathClone.attr("id","filePathClone").show();
        filePathClone.find("#filePathContent").attr("src",arr[nI]);
        filePathClone.find("#filePathDelete").on("click",function () {//删除当前上传图片地址
            for(var mI = 0 ; mI < document.body.jsLee.filePath.length; mI++ ){
                if($(this).prev().attr("src") == document.body.jsLee.filePath[mI]){
                    document.body.jsLee.filePath.splice(mI,1);
                    $(this).parents("#filePathClone").remove();
                }
            }

        });
        filePathClone.find("#filePathContent").on("click",function(){
            openWin('650', '500', 'imgBigPopup', true);
            $("#bigImg").attr("src",$(this).attr("src"));
        });
        $("#filePathTemplate").before(filePathClone);
    }
}

function clsAlertBoxCtrl$sure() {//成功弹框确定
    if (this.id == "successJump") {
        jumpUrl("objectionSubmit.html","0000000",0);
        closePopupWin();
    }
}

$(function(){
    //初始化自己封装方法
    var methodLee = new clsMethodLee();
    document.body.jsLee = methodLee;
    methodLee.init();
});