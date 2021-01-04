import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import java.util.concurrent.CompletableFuture;

public class ServerMain {
    public static void main(String[] args) {
        ServerBuilder sb = Server.builder();
        sb.http(8888);

        sb.service("/hello", (ctx, res) ->
                HttpResponse.of(
                        HttpStatus.OK,
                        MediaType.HTML_UTF_8,
                        "<h1>Hello Armeria...!</h1>"));

        Server server = sb.build();
        CompletableFuture<Void> future = server.start();
        future.join();
    }
}
