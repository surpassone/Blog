<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="fckeditor/fckeditor.js"></script>
<script>    
    window.onload = function()    
    {    
    var oFCKeditor = new FCKeditor( 'content' ) ;    
    oFCKeditor.BasePath = "/blog/fckeditor/" ; 
    oFCKeditor.ToolbarSet="Default";
    oFCKeditor.Height="400";   
    oFCKeditor.ReplaceTextarea() ;    
    }    
</script> 
</head>

<body>
请输入博文内容：
<form action="" method="get">
  <table width="603" height="168" border="0">
    <tr>
      <td width="102">标题：</td>
      <td width="491"><label>
        <input name="title" type="text" id="title" size="60" />
      </label></td>
    </tr>
    <tr>
      <td>类别：</td>
      <td><label>
        <select name="select">
          <option>小高有话说</option>
          <option>菜鸟历练</option>
          <option>生活感悟</option>
        </select>
      </label></td>
    </tr>
    <tr>
      <td bordercolor="0">内容：</td>
      <td><label>
        <textarea name="content" cols="60" rows="10" id="content"></textarea>
      </label></td>
    </tr>
    <tr>
      <td><label>
        <input type="reset" name="Submit" value="重置" />
      </label></td>
      <td><label>
        <input type="submit" name="Submit2" value="提交" />
      </label></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>
