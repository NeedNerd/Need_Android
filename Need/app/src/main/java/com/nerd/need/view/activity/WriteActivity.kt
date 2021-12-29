package com.nerd.need.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.nerd.need.R
import com.nerd.need.databinding.ActivityWriteBinding
import com.nerd.need.util.SharedPreferenceManager
import com.nerd.need.util.base.BaseActivity
import com.nerd.need.viewmodel.activity.WriteViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class WriteActivity : BaseActivity<ActivityWriteBinding, WriteViewModel>() {
    override val viewModel: WriteViewModel by viewModels()
    override val layoutRes: Int
        get() = R.layout.activity_write


    private val OPEN_GALLARY: Int = 1
    var imageFile: MultipartBody.Part? = null
    var state: String = ""
    var shareCount: Int = 0;

    override fun observerViewModel() {
        mBinding.activity = this
        getMyInfo()
        with(mViewModel) {
            uploadImageEvent.observe(this@WriteActivity, Observer {
                writePost(it, state, SharedPreferenceManager.getToken(this@WriteActivity)!!)
            })

            writePostEvent.observe(this@WriteActivity, Observer {
                Log.d("success", "게시글 작성 성공")
                val intent = Intent(this@WriteActivity, DetailActivity::class.java)
                intent.putExtra("postIdx", it)
                startActivity(intent)
                finish()
            })

            myInfoEvent.observe(this@WriteActivity, Observer {
                mBinding.writeHintTagCount.text = "(남은 나눔 횟수 : ${it.count}회)"
                shareCount = it.count
            })

            onErrorEvent.observe(this@WriteActivity, Observer {
                Toast.makeText(this@WriteActivity, "게시글 작성 중 오류가 발생했습니다.", Toast.LENGTH_SHORT)
                    .show()
                Log.d("error", "게시글 작성 중 오류 : $it")
            })
        }
    }

    fun onClickUploadBtn() {
        if (state == "0") {
            uploadImage()
        } else if (state == "1") {
            if (shareCount > 0) {
                uploadImage()
            } else {
                Toast.makeText(this, "나눔 횟수가 부족합니다. 다음 달 1일에 다시 추가됩니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun uploadImage(){
        val token = SharedPreferenceManager.getToken(this)

        if (token != null) {
            if (state != "") {
                if (imageFile != null) {
                    mViewModel.uploadImage(token, imageFile!!)
                } else {
                    Toast.makeText(this, "사진을 추가해주세요!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "나눔 & 구매 중 한 가지를 선택해주세요.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "토큰이 만료되었습니다. 다시 로그인해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickBackBtn() {
        finish()
    }

    fun getMyInfo() {
        val token = SharedPreferenceManager.getToken(this)

        if (token != null) {
            mViewModel.getMyInfo(token)
        } else {
            Toast.makeText(this, "토큰이 만료되었습니다. 다시 로그인해주세요", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickCameraBtn() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, OPEN_GALLARY)
    }

    fun onClickWantBuyBtn() {
        mBinding.writeBuyBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_tag_want_buy)
        mBinding.writeShareBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_disabled_button)

        mBinding.writeInputPrice.visibility = View.VISIBLE
        state = "0"
    }

    fun onClickWantShareBtn() {
        mBinding.writeBuyBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_disabled_button)
        mBinding.writeShareBtn.background =
            ContextCompat.getDrawable(this, R.drawable.background_tag_want_share)

        mBinding.writeInputPrice.visibility = View.INVISIBLE
        state = "1"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == OPEN_GALLARY && data != null) {
                var currentImageUri: Uri = data?.data!!
                if (Build.VERSION.SDK_INT < 28) {
                    val bitmap =
                        MediaStore.Images.Media.getBitmap(this?.contentResolver,
                            currentImageUri)
                    mBinding.writeCameraBtn.setImageBitmap(bitmap)
                } else {
                    val source =
                        ImageDecoder.createSource(this?.contentResolver!!, currentImageUri)
                    val bitmap = ImageDecoder.decodeBitmap(source)
                    mBinding.writeCameraBtn.setImageBitmap(bitmap)
                }

                val path = createCopyAndReturnRealPath(currentImageUri)

                if (path != null) {
                    val file = File(path)

                    val fileBody = RequestBody.create("image/jpeg".toMediaTypeOrNull
                        (), file)

                    imageFile = MultipartBody.Part.createFormData("file",
                        "${file.name}.jpeg",
                        fileBody)
                } else {
                    imageFile = null
                }
            }
        } else if (requestCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_SHORT).show()
        }
    }

    fun createCopyAndReturnRealPath(uri: Uri): String? {
        val context = this
        val contentResolver = context.contentResolver ?: return null

        // Create file path inside app's data dir
        val filePath = (context.applicationInfo.dataDir + File.separator
                + System.currentTimeMillis())
        val file = File(filePath)
        try {
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream: OutputStream = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream.read(buf).also { len = it } > 0) outputStream.write(buf, 0, len)
            outputStream.close()
            inputStream.close()
            return file.getAbsolutePath()
        } catch (e: Exception) {
            return null
        }
        return null
    }
}