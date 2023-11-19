/*
Copyright (c) 2003-2010, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.language = 'zh-cn';
	config.filebrowserBrowseUrl = 'ckeditor/uploader/browse.jsp';
	config.filebrowserImageBrowseUrl = 'ckeditor/uploader/browse.jsp?type=Images';
	config.filebrowserFlashBrowseUrl = 'ckeditor/uploader/browse.jsp?type=Flashs';
	config.filebrowserUploadUrl = 'ckeditor/uploader/upload.jsp';
	config.filebrowserImageUploadUrl = 'ckeditor/uploader/upload.jsp?type=Images';
	config.filebrowserFlashUploadUrl = 'ckeditor/uploader/upload.jsp?type=Flashs';
	config.filebrowserWindowWidth = '640';
	config.filebrowserWindowHeight = '680';
	//config.width = 1000; //宽度
    config.height = 480; //高度
	config.toolbar_A = [
			[ 'Source' ],
			/*
			 * ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print',
			 * 'SpellChecker', 'Scayt'],
			 * ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
			 * '/',
			 */
			[ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript',
					'Superscript' ],
			[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
					'Blockquote' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
			[ 'Link', 'Unlink', 'Anchor' ],
			[ 'Image'/* ,'Flash' */, 'Table', 'HorizontalRule'/* ,'Smiley' */,
					'SpecialChar', 'PageBreak' ], '/',
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ] /*
										 * , ['Maximize', 'ShowBlocks']
										 */
	];
	config.toolbar = 'A';
	config.removeDialogTabs = 'image:advanced;link:advanced';

};
