package bibox.in.application;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int selectedImageId;
    ImageView man,mobile,cycle,bike,car,dmobile,dcycle,dbike,dcar;
    float manX,manY;
    Canvas mobileCanvas,bikeCanvas,carCanvas,cycleCanvas;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (RelativeLayout) findViewById(R.id.drop_here);
        man = (ImageView) findViewById(R.id.man);
        mobile = (ImageView) findViewById(R.id.mobile);
        cycle = (ImageView) findViewById(R.id.cycle);
        bike = (ImageView) findViewById(R.id.bike);
        car = (ImageView) findViewById(R.id.car);

        dmobile = (ImageView) findViewById(R.id.dmobile);
        dcycle = (ImageView) findViewById(R.id.dcycle);
        dbike = (ImageView) findViewById(R.id.dbike);
        dcar = (ImageView) findViewById(R.id.dcar);

        dmobile.setVisibility(View.INVISIBLE);
        dcycle.setVisibility(View.INVISIBLE);
        dbike.setVisibility(View.INVISIBLE);
        dcar.setVisibility(View.INVISIBLE);

        manX = man.getX();
        manY = man.getY();
        Bitmap bitmap = Bitmap.createBitmap(500,300,Bitmap.Config.ARGB_8888);

        mobileCanvas = new Canvas(bitmap);
        bikeCanvas = new Canvas(bitmap);
        carCanvas = new Canvas(bitmap);
        cycleCanvas = new Canvas(bitmap);
        View.OnDragListener onDragListener = new View.OnDragListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onDrag(View v, DragEvent event) {
                final int action = event.getAction();
                switch(action){
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;
                    case DragEvent.ACTION_DROP:
                        View view = (View) event.getLocalState();
                        ViewGroup owner = (ViewGroup) view.getParent();
                        owner.removeView(view);
                        LinearLayout container = (LinearLayout) v;
                        container.addView(view);
                        view.setVisibility(View.VISIBLE);
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        dropImage(v,event);
                        return true;
                    default:break;
                }
                return true;
            }

        };

        mobile.setOnDragListener(onDragListener);
        mobile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 1;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(mobile);
                v.startDrag(data, shadow,null ,0 );
                return false;
            }
        });
        cycle.setOnDragListener(onDragListener);
        cycle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 2;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(cycle);
                v.startDrag(data, shadow,null ,0 );
                return false;
            }
        });
        bike.setOnDragListener(onDragListener);
        bike.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 3;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(bike);
                v.startDrag(data, shadow,null ,0 );
                return false;
            }
        });
        car.setOnDragListener(onDragListener);
        car.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                selectedImageId = 4;
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(car);
                v.startDrag(data, shadow,null ,0 );
                return false;
            }
        });
    }

    void dropImage(View v,DragEvent event){
        int viewId = v.getId();
        float x = event.getX()-100;
        float y = event.getY()-800;

        switch(selectedImageId){
            case 1 : dmobile.setX(x);
                dmobile.setY(y);
                dmobile.setVisibility(View.VISIBLE);
                connect(mobileCanvas,x,0.0f,0.0f,y);
                break;
            case 2 : dcycle.setX(x);
                dcycle.setY(y);
                dcycle.setVisibility(View.VISIBLE);
                connect(cycleCanvas,x,0.0f,0.0f,y);
                break;
            case 3 : dbike.setX(x);
                dbike.setY(y);
                dbike.setVisibility(View.VISIBLE);
                connect(bikeCanvas,x,0.0f,0.0f,y);
                break;
            case 4 : dcar.setX(x);
                dcar.setY(y);
                dcar.setVisibility(View.VISIBLE);
                connect(carCanvas,x,0.0f,0.0f,y);
                break;
            default : break;
        }
    }

    protected void connect(Canvas canvas,float sx, float sy, float ex, float ey) {
        canvas.drawColor(Color.GREEN);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.RED);
        p.setStrokeWidth(4.5f);
        //p.setAlpha(0x80);
        canvas.drawLine(sx, sy, ex, ey, p);
    }
}
