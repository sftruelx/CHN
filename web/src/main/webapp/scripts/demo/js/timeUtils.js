/**
 * Created by admin on 2016/6/22.
 */
function GetStringLocalDateGMT(date) {
    var gmt0 = date;
    var month = gmt0.getMonth() + 1;
    var day = gmt0.getDate();
    var hours = gmt0.getHours();
    var minutes = gmt0.getMinutes();
    var seconds = gmt0.getSeconds();
    var tz = (-1) * date.getTimezoneOffset() / 60;
    return gmt0.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day > 9 ? day : ('0' + day)) + ' ' +
        (hours > 9 ? hours : ('0' + hours)) + ':' + (minutes > 9 ? minutes : ('0' + minutes)) + ':' + (seconds > 9 ? seconds : ('0' + seconds)) + ' ' + 'GMT' + (tz >= 0 ? '+' : '') + tz;
}

function GetStringLocalDate(date) {
    var gmt0 = date;
    var month = gmt0.getMonth() + 1;
    var day = gmt0.getDate();
    var hours = gmt0.getHours();
    var minutes = gmt0.getMinutes();
    var seconds = gmt0.getSeconds();
    var tz = (-1) * date.getTimezoneOffset() / 60;
    return gmt0.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day > 9 ? day : ('0' + day)) + ' ' +
        (hours > 9 ? hours : ('0' + hours)) + ':' + (minutes > 9 ? minutes : ('0' + minutes)) + ':' + (seconds > 9 ? seconds : ('0' + seconds));
}

function GetStringLocalDateYMD(date) {
    var gmt0 = date;
    var month = gmt0.getMonth() + 1;
    var day = gmt0.getDate();
    var hours = gmt0.getHours();
    var minutes = gmt0.getMinutes();
    var seconds = gmt0.getSeconds();
    var tz = (-1) * date.getTimezoneOffset() / 60;
    return gmt0.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day > 9 ? day : ('0' + day));
}

function GetStringLocalDatefromLocalDate(date) {
    var gmt0 = date;
    var month = gmt0.getMonth() + 1;
    var day = gmt0.getDate();
    var hours = gmt0.getHours();
    var minutes = gmt0.getMinutes();
    var seconds = gmt0.getSeconds();
    var tz = (-1) * date.getTimezoneOffset() / 60;
    return gmt0.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day > 9 ? day : ('0' + day)) + ' ' +
        (hours > 9 ? hours : ('0' + hours)) + ':' + (minutes > 9 ? minutes : ('0' + minutes)) + ':' + (seconds > 9 ? seconds : ('0' + seconds)) + ' ' + 'GMT' + (tz >= 0 ? '+' : '') + tz;
}


function milliseconds2Time(milliseconds) {
    var hours = parseInt(milliseconds / 3600000);
    var minutes = parseInt(milliseconds % 3600000 / 60000);
    var seconds = parseInt(milliseconds % 60000 / 1000);

    return (hours > 9 ? hours : ('0' + hours)) + ':' + (minutes > 9 ? minutes : ('0' + minutes)) + ':' + (seconds > 9 ? seconds : ('0' + seconds));

}

function GetDateTimeQueryStringGMT0(date) {
    var gmt0 = new Date(date.getTime() + date.getTimezoneOffset() * 60000);
    var month = gmt0.getMonth() + 1;
    var day = gmt0.getDate();
    var hours = gmt0.getHours();
    var minutes = gmt0.getMinutes();
    var seconds = gmt0.getSeconds();
    return gmt0.getFullYear() + '-' + (month > 9 ? month : ('0' + month)) + '-' + (day > 9 ? day : ('0' + day)) + ' ' +
        (hours > 9 ? hours : ('0' + hours)) + ':' + (minutes > 9 ? minutes : ('0' + minutes)) + ':' + (seconds > 9 ? seconds : ('0' + seconds));
}


/*******************************************************************************************************************/
String.repeat = function (chr, count) {
    var str = "";
    for (var x = 0; x < count; x++) {
        str += chr
    }
    return str;
};
String.prototype.padL = function (width, pad) {
    if (!width || width < 1)
        return this;

    if (!pad) pad = " ";
    var length = width - this.length;
    if (length < 1) return this.substr(0, width);

    return (String.repeat(pad, length) + this).substr(0, width);
};
String.prototype.padR = function (width, pad) {
    if (!width || width < 1)
        return this;

    if (!pad) pad = " ";
    var length = width - this.length;
    if (length < 1) this.substr(0, width);

    return (this + String.repeat(pad, length)).substr(0, width);
};

Date.prototype.formatDate = function (format) {
    var date = this;
    if (!format)
        format = "MM/dd/yyyy";

    var month = date.getMonth() + 1;
    var year = date.getFullYear();

    format = format.replace("MM", month.toString().padL(2, "0"));

    if (format.indexOf("yyyy") > -1)
        format = format.replace("yyyy", year.toString());
    else if (format.indexOf("yy") > -1)
        format = format.replace("yy", year.toString().substr(2, 2));

    format = format.replace("dd", date.getDate().toString().padL(2, "0"));

    var hours = date.getHours();
    if (format.indexOf("t") > -1) {
        if (hours > 11)
            format = format.replace("t", "pm");
        else
            format = format.replace("t", "am")
    }
    if (format.indexOf("HH") > -1)
        format = format.replace("HH", hours.toString().padL(2, "0"));
    if (format.indexOf("hh") > -1) {
        if (hours > 12) hours - 12;
        if (hours == 0) hours = 12;
        format = format.replace("hh", hours.toString().padL(2, "0"));
    }
    if (format.indexOf("mm") > -1)
        format = format.replace("mm", date.getMinutes().toString().padL(2, "0"));
    if (format.indexOf("ss") > -1)
        format = format.replace("ss", date.getSeconds().toString().padL(2, "0"));
    return format;
};

// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
