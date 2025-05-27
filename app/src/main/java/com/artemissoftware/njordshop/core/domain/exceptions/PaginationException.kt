package com.artemissoftware.njordshop.core.domain.exceptions

import com.artemissoftware.njordshop.core.domain.error.NSError

class PaginationException(val error: NSError) : RuntimeException()