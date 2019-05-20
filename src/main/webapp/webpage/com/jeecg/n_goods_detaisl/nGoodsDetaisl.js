$(document).ready(function(){  
    bindKeyEvent($("#bazaarPrice"));  
    bindKeyEvent($("#weight"));  
    bindKeyEventone($("#sum_number"));
    bindKeyEvent($("#tuan_price"));
    bindKeyEvent($("#unit_price"));
  //  bindKeyEvent($(".pdd-form-input"));
  //  bindKeyEvent($("#unitPrice"));

});  
function bindKeyEvent(obj){  
    obj.keyup(function () {  
        var reg = $(this).val().match(/^(([1-9]\d*)|\d)(\.\d{0,4})?$/);  
        var txt = '';  
        if (reg != null) {  
            txt = reg[0];  
        }  
        $(this).val(txt);  
    }).change(function () {  
        $(this).keypress();  
        var v = $(this).val();  
        if (/\.$/.test(v))  
        {  
            $(this).val(v.substr(0, v.length - 1));  
        }  
    });  
}  
function bindKeyEventone(obj){  
    obj.keyup(function () {  
        var reg = $(this).val().match(/^(([1-9]\d*)|\d)(\.\d{0,0})?$/);  
        var txt = '';  
        if (reg != null) {  
            txt = reg[0];  
        }  
        $(this).val(txt);  
    }).change(function () {  
        $(this).keypress();  
        var v = $(this).val();  
        if (/\.$/.test(v))  
        {  
            $(this).val(v.substr(0, v.length - 1));  
        }  
    });  
}  