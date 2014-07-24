var plugins={
	Code:{c:'btnCode',t:'插入代码',h:1,e:function(){
		var _this=this;
		var htmlCode='<div><select id="xheCodeType"><option value="html">HTML/XML</option><option value="js">Javascript</option><option value="css">CSS</option><option value="php">PHP</option><option value="java">Java</option><option value="py">Python</option><option value="pl">Perl</option><option value="rb">Ruby</option><option value="cs">C#</option><option value="c">C++/C</option><option value="vb">VB/ASP</option><option value="">其它</option></select></div><div><textarea id="xheCodeValue" wrap="soft" spellcheck="false" style="width:300px;height:100px;" /></div><div style="text-align:right;"><input type="button" id="xheSave" value="确定" /></div>';			var jCode=$(htmlCode),jType=$('#xheCodeType',jCode),jValue=$('#xheCodeValue',jCode),jSave=$('#xheSave',jCode);
		jSave.click(function(){
			_this.loadBookmark();
			_this.pasteHTML('<br/><pre class="brush:'+jType.val()+';">\r\n'+jValue.val()+'\r\n</pre><br/>');
			_this.hidePanel();
			return false;	
		});
		_this.saveBookmark();
		_this.showDialog(jCode);
	}}
};
