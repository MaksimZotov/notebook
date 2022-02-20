package com.maksimzotov.notebook.presenter.view.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.maksimzotov.notebook.R

class RemoveDialogFragment : DialogFragment() {

    companion object {
        const val REMOVE_KEY = "SEND_CANCEL_REQUEST_KEY"

        const val EXTRA_REMOVE = "EXTRA_REMOVE"
        const val EXTRA_NOTE_ID = "EXTRA_NOTE_ID"
    }

    private val navArgs by navArgs<RemoveDialogFragmentArgs>()
    private val noteId by lazy { navArgs.noteId }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        activity?.let {
            AlertDialog.Builder(it)
                .setMessage("${getString(R.string.do_you_want_to_remove)}?")
                .setPositiveButton(R.string.yes) { _, _ ->
                    setFragmentResult(
                        REMOVE_KEY,
                        bundleOf(
                            EXTRA_REMOVE to true,
                            EXTRA_NOTE_ID to noteId
                        )
                    )
                }
                .setNegativeButton(R.string.no) { _, _ -> }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
}