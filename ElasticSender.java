import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ElasticSender {
    private DiagnosticData data;
    public ElasticSender (DiagnosticData data) {
        this.data = data;
    }
        public void sendData() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://46.101.249.205:2070")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ADSLService service = retrofit.create(ADSLService.class);

            Call<Void> send = service.sendRouterData(data);

            send.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    // success
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }

public static void main(String[] args) {
        try {
            DiagnosticData data = new DiagnosticData();
            data.routerInfo.model = "ay model";
            data.routerInfo.firmwareVersion = "ya marary";
            data.routerInfo.Mac = "Big Mac";
            data.routerInfo.firmwareDate = "awel embar7";
            data.dslInfo.UpNoise = "1";
            data.dslInfo.DownRate = "12312";
            data.dslInfo.UpRate = "456456";
            data.dslInfo.Status = "Success";
            data.dslInfo.DownNoise = "3547";
            data.dslInfo.DownAtten = "354634";
            data.dslInfo.UpAtten = "135";
            data.dslInfo.Modulation = "fuck my life";

            ElasticSender sender = new ElasticSender(data);
            sender.sendData();

           // System.out.println("This is router output " + result);

            //DSLinfo result = router.displayDSL();
            //System.out.println("this is router info " + result.Status + "\n" + result.UpRate + "\n" + result.DownRate + "\n" + result.UpNoise);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}