import io.grpc.stub.StreamObserver;
import minetest.LocationGrpc.LocationImplBase;
import minetest.LocationReply;
import minetest.UserRequest;

public class LocationImpl extends LocationImplBase {
  @Override
  public void ofUser(UserRequest request, StreamObserver<LocationReply> responseObserver) {
    System.out.println("Got user " + request.getName());
    responseObserver
      .onNext(LocationReply.newBuilder().setX(1).setY(2).setZ(3).build());
    responseObserver.onCompleted();
  }
}
