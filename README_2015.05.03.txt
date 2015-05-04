类：
fourier.fitting.Point
	二维点， 坐标 double；

fourier.fitting.FourierTransform
	拟合、变换；
	接受 ArrayList<fourier.fitting.Point>，返回 double[ ]，存储了系数；
	主算法留空；

fourier.fitting..graphic.Main
	主类
	现有功能：
		绘制界面；
		载入图像；
		在图像上绘制坐标系，范围 {x, -1, 1} {y, -1, 1}；
		图像接受鼠标点击，将点击位置坐标存入 ArrayList<fourier.fitting.Point>；
		在点击处绘制小方框；
	待增加功能：
		点击 fitting 进行拟合、变换；
		将拟合曲线绘制到图像上；
		点击 clear 清除所有点和拟合曲线；
