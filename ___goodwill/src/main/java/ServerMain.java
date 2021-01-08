import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class ServerMain {
    public static void main(String[] args) {

        /**  kafka Test **/

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("acks", "all");
        configs.put("block.on.buffer.full", "true");
        configs.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configs.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(configs);


        for (int i = 0; i < 5; i++) {
            String v = "hello"+i;
            producer.send(new ProducerRecord<String, String>("test001", v));
        }


        producer.flush();
        producer.close();


//        ServerBuilder sb = Server.builder();
//        sb.http(8888);
//
//        sb.service("/hello", (ctx, res) ->
//                HttpResponse.of(
//                        HttpStatus.OK,
//                        MediaType.HTML_UTF_8,
//                        "<h1>Hello Armeria...!</h1>"));
//
//        Server server = sb.build();
//        CompletableFuture<Void> future = server.start();
//        future.join();

//        cmd("ifconfig");

//        cmd("ps aux");
//        cmd("ifconfig");
//        cmd("sh sample.sh");
//        cmd("ifc");
//        cmd("echo \"alias ifc='ifconfig'\" >> .bash_profile");
//        cmd("pwd");
//        cmd("echo \"wdwd\"");
//        cmd("cd ~/ && echo \"alias ifc='ifconfig'\" >> .bash_profile && source .bash_profile");
//        cmd("ifc");
//        cmd("source ~/.bashrc");
    }

    public static void cmd(String m) {
        try {
            String line;
            InputStream is;
            Process p = Runtime.getRuntime().exec(m);
            p.waitFor();
            is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "MS949"));
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
            is.close();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
    }
}
