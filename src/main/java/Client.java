import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import minetest.LocationGrpc;
import minetest.LocationGrpc.LocationBlockingStub;
import minetest.LocationGrpc.LocationFutureStub;
import minetest.LocationReply;
import minetest.UserRequest;

import java.util.concurrent.TimeUnit;

public class Client {

  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", LocationServer.PORT).usePlaintext().build();

    LocationBlockingStub location = LocationGrpc.newBlockingStub(channel);

    LocationReply locationReply = location.ofUser(UserRequest.newBuilder().setName("foo").build());

    System.out.println("Location " + locationReply.getX() + ":" + locationReply.getY() + ":" + locationReply.getZ());

    channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
  }
}
