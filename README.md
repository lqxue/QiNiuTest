
##### 注册七牛云
毋庸置疑,您需要一个七牛云账号。如果您还没有七牛云账号，请通过以下步骤创建七牛云账号。

[注册七牛](https://portal.qiniu.com/)   

###### 步骤1：创建空间
首先，您需要创建一个需要上传文件的空间。登陆[七牛开发者平台](https://portal.qiniu.com/create)。

- 选择 资源主页 ，再选择 立即添加 
![创建空间](https://ok0kc3ycj.qnssl.com/kodo-image/png/quickstart01.png)
- 选择 对象存储 ，然后选择 新建存储空间 。 
![](https://ok0kc3ycj.qnssl.com/kodo-image/png/quickstart02.png)
- 在创建空间页面的存储空间名称 栏输入空间名.   
 **==注意:创建后不支持更改存储空间名称==**
- 在创建空间页面的 存储区域 栏选择该存储空间的数据中心。
- 在 访问控制 栏根据需求选择适合您的访问权限。公开空间即您可通过文件对象的 URL 直接访问，如果您要使用七牛云存储的镜像存储功能，请设置为公开空间；私有空间即对象的访问必须获得拥有者的授权才能访问。
- 选择 确定创建 。
- 当您成功创建一个空间后，七牛开发者平台会在 存储空间列表 显示您刚才创建的空间。
**[详情看使用手册](https://developer.qiniu.com/kodo/manual/console-quickstart)**
##### 集成七牛
###### 1, 依赖Gradle
如果你也是用的AndroidStudio在module的gradle中添加依赖:
```
    //七牛sdk
    compile 'com.qiniu:qiniu-android-sdk:7.3.3'
    //okhttp
    compile 'com.squareup.okhttp3:okhttp:3.4.2'
    compile 'com.squareup.okio:okio:1.11.0'
    compile 'com.google.code.gson:gson:2.8.0'
```
如果没有用AndroidStudio的话,可以参考文档下载相应的sdk[七牛Android SDk](https://developer.qiniu.com/kodo/sdk/android)
###### 2,添加权限
在AndroidManifest中添加相应的权限
```
<uses-permission 
      android:name="android.permission.INTERNET" />
<uses-permission 
      android:name="android.permission.READ_EXTERNAL_STORAGE" />
```
###### 3,初始化七牛文件上传管理器
![](http://wiki.ducetech.com/content/images/2017/03/QQ--20170322111930-2.jpg)
###### 4,获取token
![](http://wiki.ducetech.com/content/images/2017/03/QQ--20170322112125.jpg)
###### 5,定义数据上传结束后的处理动作
![](http://wiki.ducetech.com/content/images/2017/03/QQ--20170322112255.jpg)
###### 6,图片上传进度显示以及取消处理
![](http://wiki.ducetech.com/content/images/2017/03/QQ--20170322112407.jpg)
###### 7,最后上传
![](http://wiki.ducetech.com/content/images/2017/03/QQ--20170322112617.jpg)
