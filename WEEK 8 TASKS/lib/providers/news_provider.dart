import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class NewsProvider with ChangeNotifier {
  final String apiKey = "342199264ef744e8b23d3a2683dfe501"; // Your API Key
  List<dynamic> _articles = [];

  List<dynamic> get articles => _articles;

  Future<void> fetchNews({String category = 'general'}) async {
    final String url =
        "https://newsapi.org/v2/top-headlines?country=us&category=$category&apiKey=$apiKey";

    try {
      final response = await http.get(Uri.parse(url));

      if (response.statusCode == 200) {
        final data = json.decode(response.body);
        _articles = data['articles'] ?? [];
        notifyListeners();
      } else {
        throw Exception('Failed to load news');
      }
    } catch (error) {
      print("Error fetching news: $error");
    }
  }
}