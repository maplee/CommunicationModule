# CommunicationModule
应用间通信，无需root

需要通信的两个或多个app都集成该模块

## 初始化
在Application中的attachBaseContext中添加

```
ConnectionApi.install(new IPipe() {
            @Override
            public String getPackage() {
                return getPackageName();
            }

            @Override
            public void result(String data) {
                //TODO 在这里把订单状态信息data返回给H5
            }

            @Override
            public String query(String param) {
              
                return null;
            }
        });

```

在Application中的onCreate中添加

```

ConnectionApi.init(getApplicationContext());

```

## 使用
```
//package 要接受的应用包名
//msg 消息内容
ConnectionApi.update(package,msg);
```
