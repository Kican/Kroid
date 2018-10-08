package me.mo3in.kroid.commons.extensions

import android.content.Context
import android.os.Handler
import android.os.Looper
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future


private object ContextHelper {
    val handler = Handler(Looper.getMainLooper())
}

class KroidAsyncContext<T>(val weakRef: WeakReference<T>)

private val crashLogger = { throwable: Throwable -> throwable.printStackTrace() }

internal object BackgroundExecutor {
    var executor = Executors.newScheduledThreadPool(2 * Runtime.getRuntime().availableProcessors())

    fun <T> submit(task: () -> T): Future<T> = executor.submit(task)

}

/**
 * Execute [f] on the application UI thread.
 */
fun Context.runOnUiThread(f: Context.() -> Unit) {
    if (Looper.getMainLooper() === Looper.myLooper()) f() else ContextHelper.handler.post { f() }
}


fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        task: KroidAsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = KroidAsyncContext(WeakReference(this))
    return BackgroundExecutor.submit {
        return@submit try {
            context.task()
        } catch (thr: Throwable) {
            val result = exceptionHandler?.invoke(thr)
            if (result != null) {
                result
            } else {
                Unit
            }
        }
    }
}

fun <T> T.doAsync(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        executorService: ExecutorService,
        task: KroidAsyncContext<T>.() -> Unit
): Future<Unit> {
    val context = KroidAsyncContext(WeakReference(this))
    return executorService.submit<Unit> {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr)
        }
    }
}

fun <T, R> T.doAsyncResult(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        task: KroidAsyncContext<T>.() -> R
): Future<R> {
    val context = KroidAsyncContext(WeakReference(this))
    return BackgroundExecutor.submit {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr)
            throw thr
        }
    }
}

fun <T, R> T.doAsyncResult(
        exceptionHandler: ((Throwable) -> Unit)? = crashLogger,
        executorService: ExecutorService,
        task: KroidAsyncContext<T>.() -> R
): Future<R> {
    val context = KroidAsyncContext(WeakReference(this))
    return executorService.submit<R> {
        try {
            context.task()
        } catch (thr: Throwable) {
            exceptionHandler?.invoke(thr)
            throw thr
        }
    }
}
