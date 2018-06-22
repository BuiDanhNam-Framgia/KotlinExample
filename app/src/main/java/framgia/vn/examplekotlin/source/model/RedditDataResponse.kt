package framgia.vn.examplekotlin.source.model


data class RedditNewsResponse(val data: RedditDataResponse)

data class RedditDataResponse(
        val children: List<RedditNewsItemResponse>,
        val after: String?,
        val before: String?
)
data class RedditNewsItemResponse(val  data: RedditNewsItem)