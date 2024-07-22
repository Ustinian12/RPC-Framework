# RPC框架结构
## V1.0
### rpc-api
- HelloObject: 调用接口的参数
- HelloService: 调用接口
### rpc-common
- RpcRequest: 请求包的定义，包括请求接口的类名，方法名，参数类型，参数
- RpcResponse: 返回包的定义，返回状态码，返回数据，返回message，以及两个方法success或者fail
- RpcResponseCode: 状态码的定义，枚举类型。
### rpc-core
- client
  - RpcClient: 客户端，定义发送rpcRequest包的方法。
  - RpcClientProxy: 通过动态代理来发包和收包。
- server
  - RpcServer: 定义线程池以及注册接口
  - WorkerThread: 收包以及发包
### test-client
- TestClient准备参数，准备代理，进行请求，输出结果
### test-server
- TestServer注册接口，等待请求。
## V1.1
服务的注册和服务器启动分离。 
### rpc-common
- exception添加一些错误的处理。 
- enumeration添加一些错误的编号，错误信息。
对服务端进行解耦，不需要服务绑定注册。
### rpc-core
- RequestHandler: 原来WorkerThread反射调用那一块。
- RequestHandlerThread: 原来WorkerThread创建线程那一块。
- RpcServer: 将注册表，以及RequestHandlerThread加进去了。

