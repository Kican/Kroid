package me.mo3in.kroid.commons.models

open class Result(val throwable: Throwable? = null, open val errors: ResultErrors? = ResultErrors()) {


    open fun isSuccess(): Boolean = throwable == null && errors?.size == 0
}

open class DataResult<T>(open val data: T? = null, throwable: Throwable? = null, errors: ResultErrors? = ResultErrors()) : Result(throwable, errors) {
}

class ResultErrors : HashMap<String, Array<String>>() {
    fun toSeparateRows(): String {
        if (this.size == 0) return ""

        var errorMsg = "";

        this.map { entry ->
            var inlineError = "";
            entry.value.map { s -> inlineError += s + "\n" }

            errorMsg += errorMsg + inlineError + "\n"
        }
        errorMsg = errorMsg.removeSuffix("\n").removeSuffix("\n")
        return errorMsg
    }
}