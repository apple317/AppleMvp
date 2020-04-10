## 前言

  博主最近空了整理了mvp架构，其实在真实项目中小公司都是一个base，然后mvp基本都是activity、fragement对应一个
Presenter或者view，以前用drager注入，一个项目有几百个model、Presenter、view,还都是类上泛型实现，业务层应该
复用和公用、即用及注释，我这个就实现了原理靠注释、反射、动态代理、工厂模式,代码分为java和kotlin，请看对应分支，
两套代码实现思路原理差不多，博客还会分享网络kotlin组件、插件化、热更新、eventbus、butterknife,博主这些年心得，
所有技术其实原理大多都超不过100行代码，一定要动手实践，阅万千代码，不如搂一遍，实践是王道，博主马上会转flutter，
麻烦多点星哦，谢谢！

- [Kotlin Android 扩展](https://www.kotlincn.net/docs/tutorials/android-plugin.html)是一个编译器扩展， 可以让你摆脱代码中的 `findViewById()` 调用，并将其替换为合成的编译器生成的属性。
- [Anko](http://github.com/kotlin/anko) 是一个提供围绕 Android API 的 Kotlin 友好的包装器的库 ，以及一个可以用 Kotlin 代码替换布局 .xml 文件的 DSL。


###kotlin版本
```
class MainActivity : BaseMvpActivity(), SplashView, MainView {
    @CreatePresenterAnnotation(SplashPresenter::class)
    var splashPresenter: SplashPresenter? = null
    @CreatePresenterAnnotation(MainPresenter::class)
    var mainPresenter: MainPresenter? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initData() {
        btnAdvert.setOnClickListener { splashPresenter?.advertList("kotlin 广告") }
        btnSync.setOnClickListener { mainPresenter?.appSync("kotlin 同步") }
    }

    override fun appSync(msg: String) {
        btnSync.text = msg
    }

    override fun advertList(advert: String) {
        btnAdvert.text = advert
    }

    override fun initView(savedInstanceState: Bundle?) {
    }
}
```

###java版本
```
public class MainActivity extends BaseMvpActivity implements SplashView, MainView{


    @BindView(R.id.btnSync)
    Button btnSync;
    @BindView(R.id.btnAdvert)
    Button btnAdvert;


    @CreatePresenterAnnotation(SplashPresenter.class)
    SplashPresenter splashPresenter;

    @CreatePresenterAnnotation(MainPresenter.class)
    MainPresenter mainPresenter;

    @Override
    public int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @Override
    public void appSync(String msg) {
        btnSync.setText(msg);
    }

    @Override
    public void advertList(String advert) {
        btnAdvert.setText(advert);
    }


    @OnClick({R.id.btnSync, R.id.btnAdvert})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnSync:
                mainPresenter.appSync("首页初始化");
                break;
            case R.id.btnAdvert:
                splashPresenter.advertList("启动页初始化");
                break;
        }
    }
}
```
## 更新日志

**v1.0**

 - 

## 关于我

 - Email: 513888967@qq.com hushaoping518@gmail.com
 - CSDN:[https://blog.csdn.net/Apple_hsp](https://blog.csdn.net/Apple_hsp)

## Thanks

- 感谢所有优秀的开源项目

## 声明

## LICENSE

```
Copyright 2020 applehsp

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

