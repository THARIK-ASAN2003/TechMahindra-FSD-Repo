import 'package:flutter/material.dart';
import 'package:cached_network_image/cached_network_image.dart';

class ArticleDetailsScreen extends StatelessWidget {
  final dynamic article;

  const ArticleDetailsScreen({Key? key, required this.article}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text(article['title'] ?? 'Article Details')),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            CachedNetworkImage(
              imageUrl: article['urlToImage'] ?? '',
              width: double.infinity,
              height: 200,
              fit: BoxFit.cover,
              placeholder: (context, url) => Center(child: CircularProgressIndicator()),
              errorWidget: (context, url, error) =>
                  Image.network('https://picsum.photos/300/200', width: double.infinity, height: 200, fit: BoxFit.cover),
            ),
            SizedBox(height: 10),
            Text(article['title'] ?? '', style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold)),
            SizedBox(height: 10),
            Text(article['description'] ?? '', style: TextStyle(fontSize: 16)),
            SizedBox(height: 10),
            Text(article['content'] ?? '', style: TextStyle(fontSize: 14)),
          ],
        ),
      ),
    );
  }
}