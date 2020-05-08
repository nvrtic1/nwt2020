package com.example.systemevents;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: system-events.proto")
public final class SystemEventsGrpc {

  private SystemEventsGrpc() {}

  public static final String SERVICE_NAME = "SystemEvents";

  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<OuterClass.Request,
      OuterClass.Response> METHOD_START =
      io.grpc.MethodDescriptor.<OuterClass.Request, OuterClass.Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "SystemEvents", "start"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              OuterClass.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              OuterClass.Response.getDefaultInstance()))
          .build();

  public static SystemEventsStub newStub(io.grpc.Channel channel) {
    return new SystemEventsStub(channel);
  }

  public static SystemEventsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SystemEventsBlockingStub(channel);
  }

  public static SystemEventsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SystemEventsFutureStub(channel);
  }

  public static abstract class SystemEventsImplBase implements io.grpc.BindableService {

    public void start(OuterClass.Request request,
                      io.grpc.stub.StreamObserver<OuterClass.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_START, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_START,
            asyncUnaryCall(
              new MethodHandlers<
                OuterClass.Request,
                OuterClass.Response>(
                  this, METHODID_START)))
          .build();
    }
  }

  public static final class SystemEventsStub extends io.grpc.stub.AbstractStub<SystemEventsStub> {
    private SystemEventsStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsStub(channel, callOptions);
    }

    public void start(OuterClass.Request request,
                      io.grpc.stub.StreamObserver<OuterClass.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_START, getCallOptions()), request, responseObserver);
    }
  }

  public static final class SystemEventsBlockingStub extends io.grpc.stub.AbstractStub<SystemEventsBlockingStub> {
    private SystemEventsBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsBlockingStub(channel, callOptions);
    }

    public OuterClass.Response start(OuterClass.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_START, getCallOptions(), request);
    }
  }

  public static final class SystemEventsFutureStub extends io.grpc.stub.AbstractStub<SystemEventsFutureStub> {
    private SystemEventsFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SystemEventsFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SystemEventsFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SystemEventsFutureStub(channel, callOptions);
    }

    public com.google.common.util.concurrent.ListenableFuture<OuterClass.Response> start(
        OuterClass.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_START, getCallOptions()), request);
    }
  }

  private static final int METHODID_START = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SystemEventsImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SystemEventsImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_START:
          serviceImpl.start((OuterClass.Request) request,
              (io.grpc.stub.StreamObserver<OuterClass.Response>) responseObserver);
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

  private static final class SystemEventsDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return OuterClass.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SystemEventsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SystemEventsDescriptorSupplier())
              .addMethod(METHOD_START)
              .build();
        }
      }
    }
    return result;
  }
}
