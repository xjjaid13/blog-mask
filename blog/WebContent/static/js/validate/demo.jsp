<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../jquery-1.8.3.js"></script>
<script src="./jquery.validate.js"></script>
<script src="../ajaxsubmit/jquery.form.js"></script>
<script>
	$(function(){
		var v = jQuery("#form").validate({
			submitHandler: function(form) {
				jQuery(form).ajaxSubmit({
					target: "#result"
				});
			}
		});
	});
</script>
</head>
<body>
<form class="cmxform" id="form" method="post" action="./test.jsp">
	
		<p>
			<input class="aa" minlength="3" type="text" name="username" id="username" value=""/>
		</p>
		
		<p>
			<input class="aa" minlength="3" type="text" name="password" id="password" value=""/>
		</p>
		
		<p>
			<input class="submit" type="submit" value="Login"/>
		</p>
		
</form>
<div id="result">Please login!</div>
</body>
</html>