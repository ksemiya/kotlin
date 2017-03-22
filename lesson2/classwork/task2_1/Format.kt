package kss.tasks.pdf2.practice.task2_1

class Format(val width: Int, val height: Int) {

    fun rotate(): Format {
        return Format(height, width)
    }

    fun getTwiceSmaller(): Format {
        return Format(height / 2, width)
    }

    fun isAlbumOrientation(): Boolean {
        return width > height
    }

    fun isPortraitOrientation(): Boolean {
        return height < width
    }

    companion object {

        val A4 = Format(210, 297)
        val A4Album = A4.rotate()

        val A5 = A4.getTwiceSmaller()
        val A5Album = A5.rotate()

        val A6 = A5.getTwiceSmaller()
        val A6Album = A6.rotate()

    }

}
