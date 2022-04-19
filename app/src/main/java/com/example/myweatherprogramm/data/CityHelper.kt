package com.example.myweatherprogramm.data

import android.annotation.SuppressLint
import android.content.Context
import com.example.myweatherprogramm.R
import com.example.myweatherprogramm.model.CityModel

@SuppressLint("StaticFieldLeak")
object CityHelper {


    fun populateCity(context: Context): ArrayList<CityModel> {
        return arrayListOf(
            CityModel(context.getString(R.string.minsk), 24.842115429622435F, 53.146521970293676F),
            CityModel(context.getString(R.string.moscow), 37.633069F, 55.738421F),
            CityModel(context.getString(R.string.berlin), 13.4105300F, 52.5243700F),
            CityModel(context.getString(R.string.paris), 2.3488000F, 48.8534100F),
            CityModel(context.getString(R.string.warszaw), 21.0117800F, 52.2297700F),
            CityModel(context.getString(R.string.tirane), 19.8188900F, 41.3275000F),
            CityModel(context.getString(R.string.kiev),  30.5238000F , 50.4546600F),
            CityModel(context.getString(R.string.brussel),4.3487800F , 50.8504500F),
            CityModel(context.getString(R.string.london),  -0.1257400F , 51.5085300F),
            CityModel(context.getString(R.string.dublin), -6.2488900F , 53.3330600F),
            CityModel(context.getString(R.string.vaduz),  9.5215400F , 47.1415100F),
            CityModel(context.getString(R.string.luxemburg),6.1300000F, 49.6116700F),
            CityModel(context.getString(R.string.monaco), 7.4166700F,  43.7333300F),
            CityModel(context.getString(R.string.amsterdam), 4.8896900F,52.3740300F),
            CityModel(context.getString(R.string.zuric), 7.4474400F, 46.9480900F),
            CityModel(context.getString(R.string.sofia),  23.3241500F, 42.6975100F),
            CityModel(context.getString(R.string.budapest),19.0399100F, 47.4980100F),
            CityModel(context.getString(R.string.chisinau),28.8575000F, 47.0055600F),
            CityModel(context.getString(R.string.bucharest),26.1062600F ,  44.4322500F),
            CityModel(context.getString(R.string.bratislava),17.1067400F , 48.1481600F),
            CityModel(context.getString(R.string.prague),14.4207600F , 50.0880400F),
            CityModel(context.getString(R.string.copenhagen),12.5655300F ,55.6759400F),
            CityModel(context.getString(R.string.reykjavi),-21.8954100F, 64.1354800F),
            CityModel(context.getString(R.string.oslo),10.7460900F , 59.9127300F),
            CityModel(context.getString(R.string.riga),24.1058900F , 56.9460000F),
            CityModel(context.getString(R.string.vilnius),25.2798000F , 54.6891600F),
            CityModel(context.getString(R.string.helsinki),24.9354500F ,  60.1695200F),
            CityModel(context.getString(R.string.stockholm),18.0649000F , 59.3325800F),
            CityModel(context.getString(R.string.tallinn),24.7535300F , 59.4369600F),
            CityModel(context.getString(R.string.andorra),1.5210900F , 42.5077900F),
            CityModel(context.getString(R.string.sarajevo), 18.3564400F ,  43.8486400F),
            CityModel(context.getString(R.string.athens),23.7162200F , 37.9794500F),
            CityModel(context.getString(R.string.madrid),-3.7025600F , 40.4165000F),
            CityModel(context.getString(R.string.rome), 12.5113300F , 41.8919300F),
            CityModel(context.getString(R.string.malta), 14.5147200F , 35.8997200F),
            CityModel(context.getString(R.string.lisbon),-9.1333300F , 38.7166700F),
            CityModel(context.getString(R.string.skopje),21.4314100F , 41.9964600F),
            CityModel(context.getString(R.string.belgrade),20.4651300F , 44.8040100F),
            CityModel(context.getString(R.string.ljublja),14.5051300F , 46.0510800F),
            CityModel(context.getString(R.string.zagreb),15.9779800F , 45.8144400F),
            CityModel(context.getString(R.string.podgorica),19.2636100F , 42.4411100F)
        )
    }

}