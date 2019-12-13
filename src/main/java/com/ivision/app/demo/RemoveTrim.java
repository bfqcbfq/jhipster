package com.ivision.app.demo;

public class RemoveTrim {
	
	
	public static void main(String[] args) {
		String row1 = "Jun-jun.Shen@ivision-china.cn";
		String row2 = "dawn-025@hotmail.com";
		String row3 = "435275177@qq.com";
		String row4 = "youku@shyouku.com";
		String row5 = "lei.wang@ivision-China.Cn";
		String row6 = "jia-shengzhang@ivision-china.Cn";
		String row7 = "99606299@qq.com";
		String row8 = "wan-jun@163.com";
		String row9 = "yinghua.cheese@outlook.com";
		String row10 = "QING.YUAN@ivision-china.Cn";
		
		String combinStr = row1+row2+row3+row4+row5+row6+row7+row8+row9+row10;
		System.out.println(combinStr);

		
		int length = combinStr.length();
		
		System.out.println(length);
		countKannji();
		
		
	}
	
	
	public static void countKannji() {
		
		String row1 = "沈俊珺" + 
				"邓黎明" + 
				"曹勇" + 
				"张晓敬" + 
				"王磊" + 
				"张家最" + 
				"张国磊" + 
				"王军" + 
				"吴英华" + 
				"袁青" + 
				"上海菱威深信息技术有限公司" + 
				"上海中蕊国际有限公司" + 
				"上海菱成深信息技术有限公司" + 
				"上海优酷视讯有限公司" + 
				"上海菱威深信息技术有限公饲" + 
				"上海菱威深信息技术有限公司" + 
				"上海申铁信息技术有限公司" + 
				"上海大河有限公司" + 
				"上海求步信息系统有限公网" + 
				"上海菱威深信息技术有限公司" + 
				"增加更加详细的参数说明。" + 
				"官网增加名产品更详细参数" + 
				"暂时没有" + 
				"能够增加免费试用时长" + 
				"希望贵司继续制造更好的产品" + 
				"没有" + 
				"猪益求猪再创辉學" + 
				"希望增加新的种类" + 
				"加油!" + 
				"不错";
		
		System.out.println("填写的汉字数："+row1.length());


		String row3 = "AC	CD	A	BC	ACD	B	A	CD	B	B" + 
				"AC	CD	A	BC	ACD	B	A	CD	B	B" + 
				"ABD	BCE	A	BCD	CD	C	B	BC	B	B" + 
				"ACE	CD	A	CE	BD	D	B	BCD	CB	B" + 
				"BCD	BC	B	BDCAE	AB	B	A	B	BCD	A" + 
				"AB	CD	A	E	A	C	B	DACB	ACD	A" + 
				"ABD	BCE	B	BCADE	ACD	C	A	C	A	B" + 
				"AE	BD	C	ABcD	AB	C	A	BCD	CD	A" + 
				"AB	BC	B	BCADE	AC	B	B	B	A	A" + 
				"ACD	AD	B	CBADE	AD	B	A	ABC	AB	A";
		String replace = row3.replaceAll("\\s*", "");
		
		System.out.println("选择项填写字符数："+replace.length());
		
		
		String row4 = "13524633381	Jun-jun.Shen@ivision-china.cn" + 
				"021-20808835	dawn-025@hotmail.com" + 
				"18818286756	435275177@qq.com" + 
				"021-58832221	youku@shyouku.com" + 
				"13761256213	lei.wang@ivision-China.Cn" + 
				"20809678	jia-shengzhang@ivision-china.Cn" + 
				"13663195606	99606299@qq.com" + 
				"021-0345678	wan-jun@163.com" + 
				"18621996446	yinghua.cheese@outlook.com" + 
				"20809630	QING.YUAN@ivision-china.Cn";
		String replaceAll = row4.replaceAll("\\s*", "");
		
		System.out.println("英数填写字符数："+replaceAll.length());
		
		
	}

}
