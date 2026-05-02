# AllTrans v1.9.7

Android 16 / Vector compatibility and reliability update.

## Implemented Functionality
- Added Android 16-friendly shared-preference access path for hooked processes.
- Added settings-provider proxy fallback for cross-process preference reads.
- Added robust Google ML Kit source-language auto-detection with concrete fallback chain:
  - ML Kit primary detection
  - Unicode-script evidence for CJK/Korean/Japanese text
  - ML Kit candidate-confidence fallback
  - App-locale fallback
- Added translation model readiness gating to prevent empty/failed first-use translations.
- Added startup sentinel bypass to avoid Discord startup crash edge case.
- Added toast translation hook coverage (`makeText`, `setText`, notification toast pipeline).
- Improved notification person-name replacement correctness (`Objects.equals`).
- Removed Firebase analytics/crashlytics dependencies for leaner privacy-first release.
- Hardened settings hook install path to avoid process-killing behavior on host-hook failure.
- Reduced verbose plaintext translation logging in production paths.

## Simple Usage Instructions
- Install AllTrans v1.9.7 APK.
- Enable module in LSPosed/Vector.
- Scope required target apps.
- In AllTrans global settings, set target language.
- For Google provider, source language now defaults to `auto`.
- Force-stop and reopen target apps after preference changes.

## Build Artifact
- `alltrans-v1.9.7-release.apk`
- SHA-256: `BA723593FBCC25E1BAE0DCC18A8A933F615F8AD8E05AC17C199CBE6D410C2845`