package com.example.strechingstudio.controller;

import com.example.strechingstudio.service.SubscriptionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping
    public String showSubscriptions(Model model) {
        model.addAttribute("subscriptions", subscriptionService.getAllSubscriptions());
        return "subscriptions";
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam Long subscriptionId) {
        return "redirect:/subscriptions/payment";
    }

    @GetMapping("/payment")
    public String paymentPage() {

        return "payment";
    }

    @PostMapping("/pay")
    public String pay() {
        return "redirect:/subscriptions/success";
    }

    @GetMapping("/success")
    public String paymentSuccess() {
        return "payment_success";
    }
}