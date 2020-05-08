package com.example.systemevents;

import org.springframework.stereotype.Component;
import com.example.systemevents.SystemEventsGrpc.SystemEventsImplBase;
import com.example.systemevents.OuterClass.Request;
import com.example.systemevents.OuterClass.Response;
import io.grpc.stub.StreamObserver;

@Component
public class SystemEventsImpl extends SystemEventsImplBase {
	
	@Override
	public void start(Request request, StreamObserver<Response> responseObserver) {
 
        String odgovorNaAkciju = new StringBuilder()
          .append("Service, ")
          .append(request.getTimestamp())
          .append(request.getNazivMikroservisa())
          .append(request.getTip())
          .append(request.getNazivResursa())
          .toString();
 
        Response response = Response.newBuilder()
          .setOdgovorNaAkciju(odgovorNaAkciju)
          .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
	 
	
}
