package com.kkennib.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.24.0)",
    comments = "Source: GreetingService.proto")
public final class GreetingServiceGrpc {

  private GreetingServiceGrpc() {}

  public static final String SERVICE_NAME = "com.kkennib.grpc.GreetingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getCreateWorkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createWork",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getCreateWorkMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getCreateWorkMethod;
    if ((getCreateWorkMethod = GreetingServiceGrpc.getCreateWorkMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getCreateWorkMethod = GreetingServiceGrpc.getCreateWorkMethod) == null) {
          GreetingServiceGrpc.getCreateWorkMethod = getCreateWorkMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createWork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("createWork"))
              .build();
        }
      }
    }
    return getCreateWorkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getBeginWorkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "beginWork",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getBeginWorkMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getBeginWorkMethod;
    if ((getBeginWorkMethod = GreetingServiceGrpc.getBeginWorkMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getBeginWorkMethod = GreetingServiceGrpc.getBeginWorkMethod) == null) {
          GreetingServiceGrpc.getBeginWorkMethod = getBeginWorkMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "beginWork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("beginWork"))
              .build();
        }
      }
    }
    return getBeginWorkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getResumeWorkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "resumeWork",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getResumeWorkMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getResumeWorkMethod;
    if ((getResumeWorkMethod = GreetingServiceGrpc.getResumeWorkMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getResumeWorkMethod = GreetingServiceGrpc.getResumeWorkMethod) == null) {
          GreetingServiceGrpc.getResumeWorkMethod = getResumeWorkMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "resumeWork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("resumeWork"))
              .build();
        }
      }
    }
    return getResumeWorkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getTerminateWorkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "terminateWork",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getTerminateWorkMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getTerminateWorkMethod;
    if ((getTerminateWorkMethod = GreetingServiceGrpc.getTerminateWorkMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getTerminateWorkMethod = GreetingServiceGrpc.getTerminateWorkMethod) == null) {
          GreetingServiceGrpc.getTerminateWorkMethod = getTerminateWorkMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "terminateWork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("terminateWork"))
              .build();
        }
      }
    }
    return getTerminateWorkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getPauseWorkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pauseWork",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getPauseWorkMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getPauseWorkMethod;
    if ((getPauseWorkMethod = GreetingServiceGrpc.getPauseWorkMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getPauseWorkMethod = GreetingServiceGrpc.getPauseWorkMethod) == null) {
          GreetingServiceGrpc.getPauseWorkMethod = getPauseWorkMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pauseWork"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("pauseWork"))
              .build();
        }
      }
    }
    return getPauseWorkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGetCurrentStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getCurrentState",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGetCurrentStateMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGetCurrentStateMethod;
    if ((getGetCurrentStateMethod = GreetingServiceGrpc.getGetCurrentStateMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetCurrentStateMethod = GreetingServiceGrpc.getGetCurrentStateMethod) == null) {
          GreetingServiceGrpc.getGetCurrentStateMethod = getGetCurrentStateMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getCurrentState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getCurrentState"))
              .build();
        }
      }
    }
    return getGetCurrentStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkList> getGetWorkTableMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getWorkTable",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkList> getGetWorkTableMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkList> getGetWorkTableMethod;
    if ((getGetWorkTableMethod = GreetingServiceGrpc.getGetWorkTableMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGetWorkTableMethod = GreetingServiceGrpc.getGetWorkTableMethod) == null) {
          GreetingServiceGrpc.getGetWorkTableMethod = getGetWorkTableMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getWorkTable"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkList.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("getWorkTable"))
              .build();
        }
      }
    }
    return getGetWorkTableMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGreetingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "greeting",
      requestType = com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.class,
      responseType = com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
      com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGreetingMethod() {
    io.grpc.MethodDescriptor<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getGreetingMethod;
    if ((getGreetingMethod = GreetingServiceGrpc.getGreetingMethod) == null) {
      synchronized (GreetingServiceGrpc.class) {
        if ((getGreetingMethod = GreetingServiceGrpc.getGreetingMethod) == null) {
          GreetingServiceGrpc.getGreetingMethod = getGreetingMethod =
              io.grpc.MethodDescriptor.<com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat, com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "greeting"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GreetingServiceMethodDescriptorSupplier("greeting"))
              .build();
        }
      }
    }
    return getGreetingMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingServiceStub newStub(io.grpc.Channel channel) {
    return new GreetingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreetingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void createWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateWorkMethod(), responseObserver);
    }

    /**
     */
    public void beginWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBeginWorkMethod(), responseObserver);
    }

    /**
     */
    public void resumeWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getResumeWorkMethod(), responseObserver);
    }

    /**
     */
    public void terminateWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getTerminateWorkMethod(), responseObserver);
    }

    /**
     */
    public void pauseWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getPauseWorkMethod(), responseObserver);
    }

    /**
     */
    public void getCurrentState(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCurrentStateMethod(), responseObserver);
    }

    /**
     */
    public void getWorkTable(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkList> responseObserver) {
      asyncUnimplementedUnaryCall(getGetWorkTableMethod(), responseObserver);
    }

    /**
     */
    public void greeting(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGreetingMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateWorkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_CREATE_WORK)))
          .addMethod(
            getBeginWorkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_BEGIN_WORK)))
          .addMethod(
            getResumeWorkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_RESUME_WORK)))
          .addMethod(
            getTerminateWorkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_TERMINATE_WORK)))
          .addMethod(
            getPauseWorkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_PAUSE_WORK)))
          .addMethod(
            getGetCurrentStateMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_GET_CURRENT_STATE)))
          .addMethod(
            getGetWorkTableMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkList>(
                  this, METHODID_GET_WORK_TABLE)))
          .addMethod(
            getGreetingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat,
                com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>(
                  this, METHODID_GREETING)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingServiceStub extends io.grpc.stub.AbstractStub<GreetingServiceStub> {
    private GreetingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceStub(channel, callOptions);
    }

    /**
     */
    public void createWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateWorkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void beginWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBeginWorkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void resumeWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getResumeWorkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void terminateWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTerminateWorkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void pauseWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPauseWorkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCurrentState(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetCurrentStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getWorkTable(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkList> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetWorkTableMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void greeting(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request,
        io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGreetingMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingServiceBlockingStub extends io.grpc.stub.AbstractStub<GreetingServiceBlockingStub> {
    private GreetingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse createWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getCreateWorkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse beginWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getBeginWorkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse resumeWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getResumeWorkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse terminateWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getTerminateWorkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse pauseWork(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getPauseWorkMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> getCurrentState(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingServerStreamingCall(
          getChannel(), getGetCurrentStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkList getWorkTable(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getGetWorkTableMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse greeting(com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return blockingUnaryCall(
          getChannel(), getGreetingMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingServiceFutureStub extends io.grpc.stub.AbstractStub<GreetingServiceFutureStub> {
    private GreetingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> createWork(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateWorkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> beginWork(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getBeginWorkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> resumeWork(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getResumeWorkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> terminateWork(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getTerminateWorkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> pauseWork(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getPauseWorkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkList> getWorkTable(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getGetWorkTableMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse> greeting(
        com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat request) {
      return futureUnaryCall(
          getChannel().newCall(getGreetingMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_WORK = 0;
  private static final int METHODID_BEGIN_WORK = 1;
  private static final int METHODID_RESUME_WORK = 2;
  private static final int METHODID_TERMINATE_WORK = 3;
  private static final int METHODID_PAUSE_WORK = 4;
  private static final int METHODID_GET_CURRENT_STATE = 5;
  private static final int METHODID_GET_WORK_TABLE = 6;
  private static final int METHODID_GREETING = 7;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreetingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_WORK:
          serviceImpl.createWork((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_BEGIN_WORK:
          serviceImpl.beginWork((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_RESUME_WORK:
          serviceImpl.resumeWork((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_TERMINATE_WORK:
          serviceImpl.terminateWork((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_PAUSE_WORK:
          serviceImpl.pauseWork((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_GET_CURRENT_STATE:
          serviceImpl.getCurrentState((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        case METHODID_GET_WORK_TABLE:
          serviceImpl.getWorkTable((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkList>) responseObserver);
          break;
        case METHODID_GREETING:
          serviceImpl.greeting((com.kkennib.grpc.GreetingServiceOuterClass.KeywordFormat) request,
              (io.grpc.stub.StreamObserver<com.kkennib.grpc.GreetingServiceOuterClass.WorkResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreetingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.kkennib.grpc.GreetingServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GreetingService");
    }
  }

  private static final class GreetingServiceFileDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier {
    GreetingServiceFileDescriptorSupplier() {}
  }

  private static final class GreetingServiceMethodDescriptorSupplier
      extends GreetingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreetingServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreetingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingServiceFileDescriptorSupplier())
              .addMethod(getCreateWorkMethod())
              .addMethod(getBeginWorkMethod())
              .addMethod(getResumeWorkMethod())
              .addMethod(getTerminateWorkMethod())
              .addMethod(getPauseWorkMethod())
              .addMethod(getGetCurrentStateMethod())
              .addMethod(getGetWorkTableMethod())
              .addMethod(getGreetingMethod())
              .build();
        }
      }
    }
    return result;
  }
}
