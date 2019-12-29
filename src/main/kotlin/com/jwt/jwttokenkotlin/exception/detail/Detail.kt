package com.jwt.jwttokenkotlin.exception.detail

data class Detail (
     val title: String? = null,
     val status: Int = 0,
     val detail: String? = null,
     val timestamp: Long? = null,
     val developerMessage: String? = null,
     val field: String? = null,
     val fieldMessage: String? = null
){
    data class Builder(
            var title: String? = null,
            var status: Int = 0,
            var detail: String? = null,
            var timestamp: Long? = null,
            var developerMessage: String? = null,
            var field: String? = null,
            var fieldMessage: String? = null) {

        fun title(title: String) = apply { this.title = title }
        fun status(status: Int) = apply { this.status = status }
        fun detail(detail: String?) = apply { this.detail = detail }
        fun timestamp(timestamp: Long) = apply { this.timestamp = timestamp }
        fun developerMessage(developerMessage: String) = apply { this.developerMessage = developerMessage }
        fun field(field: String) = apply { this.field = field }
        fun fieldMessage(fieldMessage: String) = apply { this.fieldMessage = fieldMessage }
        fun build() = Detail(title, status, detail, timestamp, developerMessage, field, fieldMessage)
    }
}