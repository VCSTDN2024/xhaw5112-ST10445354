package com.varsitycollege.upskill2.ui.contactus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.varsitycollege.upskill2.databinding.FragmentContactusBinding

class ContactUsFragment : Fragment() {

    private var _binding: FragmentContactusBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load the Google Map inside the WebView
        val mapWebView: WebView = binding.contactMapWebView
        val webSettings: WebSettings = mapWebView.settings
        webSettings.javaScriptEnabled = true
        mapWebView.webViewClient = WebViewClient()

        // Load Google Maps embedded HTML content
        val mapHtml = """
            <html>
                <body>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d57307.27459277851!2d28.012259211350425!3d-26.14117094128887!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x1e950c0e05305f7f%3A0xd4269c0854a67ead!2sSchonland%20Research%20Centre%2C%201%20Jan%20Smuts%20Ave%2C%20Braamfontein%2C%20Johannesburg%2C%202017!5e0!3m2!1sen!2sza!4v1728990628397!5m2!1sen!2sza" width="100%" height="100%" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
                </body>
            </html>
        """
        mapWebView.loadData(mapHtml, "text/html", "UTF-8")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
