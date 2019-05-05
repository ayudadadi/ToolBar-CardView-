package com.example.yls.demoa;

import android.graphics.Color;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.ArcOptions;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineSearch;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MapView mMapView = null;
    private BaiduMap mBaiduMap;
    private LocationClient mLocationClient;
    private LatLng mlatLng;
    private AutoCompleteTextView auto_text;
    private SuggestionSearch suggestionSearch;
    private String key;
    private TextWatcher textWatcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        mMapView.setLogoPosition(LogoPosition.logoPostionCenterTop);

        findViewById(R.id.b8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        findViewById(R.id.b7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoutePlanSearch routePlanSearch = RoutePlanSearch.newInstance();
                routePlanSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
                    @Override
                    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

                    }

                    @Override
                    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                    }

                    @Override
                    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                    }

                    @Override
                    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

                    }

                    @Override
                    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                    }

                    @Override
                    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                    }
                });
                PlanNode start = PlanNode.withCityNameAndPlaceName("广州","广东交通职业技术学院");
                PlanNode end = PlanNode.withCityNameAndPlaceName("广州","广州塔");
                routePlanSearch.walkingSearch(new WalkingRoutePlanOption().from(start).to(end));

            }
        });


        findViewById(R.id.b6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BusLineSearch busLineSearch = BusLineSearch.newInstance();
                busLineSearch.setOnGetBusLineSearchResultListener(new OnGetBusLineSearchResultListener() {
                    @Override
                    public void onGetBusLineResult(BusLineResult busLineResult) {
                        Log.e(TAG,busLineResult.toString());

                    }
                });
                busLineSearch.searchBusLine(new BusLineSearchOption().city("广州")
                .uid("aa82413e7f6bf160f5b91651"));
            }
        });



        auto_text =findViewById(R.id.auto_text);
        findViewById(R.id.b5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        suggestionSearch = SuggestionSearch.newInstance();
        suggestionSearch.setOnGetSuggestionResultListener(new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                List<SuggestionResult.SuggestionInfo> suggestions = suggestionResult.getAllSuggestions();
                final ArrayList<String> strList = new ArrayList<>();
                for (int i =0;i<suggestions.size();i++){
                    Log.e(TAG,suggestions.get(i).toString());
                    strList.add(suggestions.get(i).getKey());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,strList);
                auto_text.setAdapter(adapter);
                auto_text.removeTextChangedListener(textWatcher);
                auto_text.setText(key);
                auto_text.setSelection(key.length());
                auto_text.addTextChangedListener(textWatcher);

            }
        });

        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                key = editable.toString();
                if(key.length()==0){
                    return;
                }
                String key = editable.toString();
                suggestionSearch.requestSuggestion(new SuggestionSearchOption().
                        city("广州").keyword("交通"));

            }
        };

        final PoiSearch poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                List<PoiInfo> poiList =poiResult.getAllPoi();
                for(int i =0;i<poiList.size();i++){
                    Log.e(TAG,poiList.get(i).toString());
                }


                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(mBaiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {
                Log.e(TAG,poiDetailSearchResult.getPoiDetailInfoList().get(0).toString());
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        });
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                poiSearch.searchInCity(new PoiCitySearchOption().city("广州").keyword("84路"));

            }
        });
        findViewById(R.id.b2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 设置矩形检索区域
                 */
                LatLngBounds searchBounds = new LatLngBounds.Builder()
                        .include(new LatLng( mlatLng.latitude+0.05, mlatLng.latitude-0.05))
                        .include(new LatLng( mlatLng.latitude-0.05, mlatLng.latitude+0.05))
                        .build();
                /**
                 * 在searchBounds区域内检索餐厅
                 */
                poiSearch.searchInBound(new PoiBoundSearchOption()
                        .bound(searchBounds)
                        .keyword("学校"));
            }
        });
        findViewById(R.id.b3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poiSearch.searchNearby(new PoiNearbySearchOption().location(mlatLng)
                .keyword("美食").radius(1000));
            }
        });
        findViewById(R.id.b4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poiSearch.searchPoiDetail(new PoiDetailSearchOption().poiUids("9fcc825914e5bbe303ec0137"));
            }
        });

        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mlatLng = latLng;
                Toast.makeText(MainActivity.this,"长按 纬度"+latLng.latitude
                        +"经度"+latLng.longitude,Toast.LENGTH_SHORT
                ).show();
                //定义Maker坐标点
                LatLng point = latLng;
//构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.maker);
//构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .title("天河客运站")
                        .position(point)
                        .icon(bitmap);
//在地图上添加Marker，并显示
                mBaiduMap.addOverlay(option);

                /*绘制折线*/
                /*drawMPloyline(latLng);*/
                /*绘制长方形*/
                /*drawRectangle(latLng);*/
                /*绘制弧线*/
                /*drawArc(latLng);*/
                /*绘制圆*/
                /*drawCircle(latLng);*/
                /*添加文字*/
                /*drawText(latLng);*/
                /*绘制信息框*/
                /*drawInfoWindow(latLng);*/

            }
        });


        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {

            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MainActivity.this,marker.getTitle(),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mBaiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng latLng) {
                Toast.makeText(MainActivity.this,"双击 纬度"+latLng.latitude
                        +"经度"+latLng.longitude,Toast.LENGTH_SHORT
                ).show();

            }
        });
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MainActivity.this,"单击 纬度"+latLng.latitude
                        +"经度"+latLng.longitude,Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                Toast.makeText(MainActivity.this,"MapPoi单击 纬度"+mapPoi.getName()
                        +"坐标"+mapPoi.getPosition().toString(),Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

//普通地图 ,mBaiduMap是地图控制器对象
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMyLocationEnabled(true);

        MyLocationConfiguration myLocationConiguration = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.FOLLOWING,
        true,
                BitmapDescriptorFactory.fromResource(R.drawable.tubiao),
        0xAAFFFF88,
        0xAA00FF00);

        mBaiduMap.setMyLocationConfiguration(myLocationConiguration);
        //定位初始化
        mLocationClient = new LocationClient(this);

//通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

//设置locationClientOption
        mLocationClient.setLocOption(option);

//注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
//开启地图定位图层
        mLocationClient.start();
    }

    private void drawInfoWindow(LatLng latLng) {
//用来构造InfoWindow的Button
        Button button = new Button(getApplicationContext());
        button.setBackgroundResource(R.drawable.word);
        button.setText("InfoWindow");

//构造InfoWindow
//point 描述的位置点
//-100 InfoWindow相对于point在y轴的偏移量
        InfoWindow mInfoWindow = new InfoWindow(button, latLng, -50);

//使InfoWindow生效
        mBaiduMap.showInfoWindow(mInfoWindow);
    }

    private void drawText(LatLng latLng) {
        //文字覆盖物位置坐标
        LatLng llText = latLng;

//构建TextOptions对象
        OverlayOptions mTextOptions = new TextOptions()
                .text("我爱广交") //文字内容
                .bgColor(0xAAFFFF00) //背景色
                .fontSize(24) //字号
                .fontColor(0xFFFF00FF) //文字颜色
                .rotate(-30) //旋转角度
                .position(llText);

//在地图上显示文字覆盖物
        Overlay mText = mBaiduMap.addOverlay(mTextOptions);
    }

    private void drawCircle(LatLng latLng) {
//圆心位置
        LatLng center = latLng;

//构造CircleOptions对象
        CircleOptions mCircleOptions = new CircleOptions().center(center)
                .radius(5000)
                .fillColor(0xAA0000FF) //填充颜色
                .stroke(new Stroke(5, 0xAA00ff00)); //边框宽和边框颜色

//在地图上显示圆
        Overlay mCircle = mBaiduMap.addOverlay(mCircleOptions);
    }

    private void drawArc(LatLng latLng) {
        // 添加弧线坐标数据
        LatLng p1 = latLng;
        LatLng p2 = new LatLng(p1.latitude-0.08, p1.longitude+0.08);//中间点
        LatLng p3 = new LatLng(p1.latitude, p1.longitude+0.16);//终点

//构造ArcOptions对象
        OverlayOptions mArcOptions = new ArcOptions()
                .color(Color.RED)
                .width(10)
                .points(p1, p2, p3);

//在地图上显示弧线
        Overlay mArc = mBaiduMap.addOverlay(mArcOptions);
    }

    private void drawRectangle(LatLng latLng) {
        LatLng p1 = latLng;
        LatLng p2 = new LatLng(p1.latitude-0.05,p1.longitude);
        LatLng p3 = new LatLng(p2.latitude,p2.longitude+0.05);
        LatLng p4 = new LatLng(p3.latitude+0.05,p3.longitude);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p1);
        //设置折线的属性
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xAAFF0000)
                .points(points);
//在地图上绘制折线
//mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
    }

    private void drawMPloyline(LatLng latLng) {
        LatLng p1 = latLng;
        LatLng p2 = new LatLng(p1.latitude-0.005,p1.longitude+0.005);
        LatLng p3 = new LatLng(p1.latitude,p1.longitude+0.01);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        //设置折线的属性
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xAAFF0000)
                .points(points);
//在地图上绘制折线
//mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
    }

    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        mLocationClient.stop();
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mMapView == null){
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
        }
    }
}
