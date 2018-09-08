package io.droidplate.wundertest.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.support.v4.util.LruCache
import android.support.v7.content.res.AppCompatResources
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object Bitmaps {

    private val descriptorCache = LruCache<Int, BitmapDescriptor>(1024 * 1024) // 1MB

    fun descriptor(context: Context, resId: Int): BitmapDescriptor {

        var des = descriptorCache.get(resId)

        if (des != null) return des

        val d = AppCompatResources.getDrawable(context, resId)
                ?: throw Resources.NotFoundException("drawable with id $resId not found");

        val b = when (d) {

            is BitmapDrawable -> d.bitmap

            else -> {
                val b = Bitmap.createBitmap(d.intrinsicWidth, d.intrinsicHeight, Bitmap.Config.ARGB_8888)
                val canvas = Canvas(b)

                d.setBounds(0, 0, canvas.width, canvas.height)
                d.draw(canvas)

                b
            }
        }

        des = BitmapDescriptorFactory.fromBitmap(b)

        descriptorCache.put(resId, des)

        return des
    }
}