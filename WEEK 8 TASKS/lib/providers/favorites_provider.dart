import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';

class FavoritesProvider with ChangeNotifier {
  List<dynamic> _favorites = [];

  List<dynamic> get favorites => _favorites;

  Future<void> loadFavorites() async {
    final prefs = await SharedPreferences.getInstance();
    final String? savedData = prefs.getString('favorites');

    if (savedData != null) {
      _favorites = json.decode(savedData);
      notifyListeners();
    }
  }

  Future<void> toggleFavorite(dynamic article) async {
    if (_favorites.any((fav) => fav['url'] == article['url'])) {
      _favorites.removeWhere((fav) => fav['url'] == article['url']);
    } else {
      _favorites.add(article);
    }

    final prefs = await SharedPreferences.getInstance();
    prefs.setString('favorites', json.encode(_favorites));
    notifyListeners();
  }
}