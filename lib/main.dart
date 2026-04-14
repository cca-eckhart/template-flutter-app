import 'package:flutter/material.dart';

void main() {
  runApp(const MeinAppTemplate());
}

class MeinAppTemplate extends StatelessWidget {
  const MeinAppTemplate({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'HTLinn App',
      theme: ThemeData(
        useMaterial3: true,
        colorSchemeSeed: Colors.amber, // Die Grundfarbe der App
      ),
      home: const StartSeite(),
    );
  }
}

class StartSeite extends StatefulWidget {
  const StartSeite({super.key});

  @override
  State<StartSeite> createState() => _StartSeiteState();
}

class _StartSeiteState extends State<StartSeite> {
  // Eine einfache Variable für den Text
  String anzeigeText = 'Willkommen in deiner App!';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('HTLinn Starter Projekt'),
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            // Ein Icon als Platzhalter für ein Logo oder Bild
            const Icon(Icons.rocket_launch, size: 80, color: Colors.amber),

            const SizedBox(height: 20),

            // Der Text, der sich ändern kann
            Text(
              anzeigeText,
              style: const TextStyle(fontSize: 20, fontWeight: FontWeight.w500),
            ),

            const SizedBox(height: 40),

            // Ein einfacher Button für die Interaktion
            ElevatedButton(
              onPressed: () {
                // Mit setState sagen wir Flutter, dass sich etwas geändert hat
                setState(() {
                  anzeigeText = 'Der Button wurde gedrückt!';
                });
              },
              child: const Text('Hier klicken'),
            ),
          ],
        ),
      ),
    );
  }
}
