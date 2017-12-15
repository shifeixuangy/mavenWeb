//定义中文消息
var cnmsg = { required: "必填",
                remote: "请修正该字段",
                email: "请输入正确格式的电子邮件",
                url: "请输入合法的网址",
                date: "请输入合法的日期",
                dateISO: "请输入合法的日期 (ISO).",
                number: "请输入合法的数字",
                digits: "只能输入整数",
                creditcard: "请输入合法的信用卡号",
                equalTo: "请再次输入相同的值",
                accept: "请输入拥有合法后缀名的字符串",
                maxlength: jQuery.format("请输入一个长度最多是 {0} 位的字符串"),
                minlength: jQuery.format("请输入一个长度最少是 {0} 位的字符串"),
                rangelength: jQuery.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
                range: jQuery.format("请输入一个介于 {0} 和 {1} 之间的值"),
                max: jQuery.format("请输入一个最大为 {0} 的值"),
                min: jQuery.format("请输入一个最小为 {0} 的值")
};
jQuery.extend(jQuery.validator.messages, cnmsg);


//拓展validate
$.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|18[0-9]{1})+\d{8})$/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请输入正确的手机号码");

//拓展validate输入字符串不能大于70
$.validator.addMethod("maxlengthText", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length<70);
}, "输入字段不能大于70字符");

//输入坐标不能大于15字符
$.validator.addMethod("latLngText", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length<15);
}, "输入坐标字段不能大于15字符");
//验证是垃圾处理场表单
$.validator.addMethod("factoryNameTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "垃圾处理场名称不能为空");
$.validator.addMethod("designScaleTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "设计规模能不能为空，请输入数值");
$.validator.addMethod("treatmentProcessTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=10);
}, "处理工艺不能为空，请选择工艺");
$.validator.addMethod("statusTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=10);
}, "状态不能为空，请选择状态");
$.validator.addMethod("typeTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=10);
}, "类型不能为空，请选择类型");
$.validator.addMethod("coordinateTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "坐标不能为空，请点击地图获取坐标");
$.validator.addMethod("areaTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "区域坐标不能为空，请编辑区域");
$.validator.addMethod("cityTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "城市不能为空，请选择城市");
$.validator.addMethod("countyTestRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&value!=1);
}, "区县不能为空，请选择区县");

//验证设备表单
$.validator.addMethod("deviceIdRequired", function(value, element) {
    var length = value.length;
    return this.optional(element) || (length!=0&&/^(\d|[a-zA-Z])+$/.test(value));
}, "请输入正确格式的设备号，不能含有中文");

jQuery.validator.addMethod("isPhoneNumber", function (value, element) {
    var tm=/(^1[3|4|5|7|8]\d{9}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/;
    return this.optional(element) || (tm.test(value));
}, "格式不对");//可以自定义默认提示信息




