
#import "RNGunzip.h"

@interface RNGunzip ()

@end

@implementation RNGunzip

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(gunzip,
                 filePath: (NSString *) source
                 toFolder: (NSString *) folder
                 force: (BOOL) force
                 resolver: (RCTPromiseResolveBlock)resolve
                 rejecter: (RCTPromiseRejectBlock)reject)
{
    
    
    NSFileManager *manager = [NSFileManager defaultManager];

    if (![manager fileExistsAtPath:source]) {
        reject(@"-2", @"file not found", nil);
        return;
    }

    if ([manager fileExistsAtPath:folder]) {
        if (!force) {
            reject(@"-2", @"file/folder already exists", nil);
            return;
        }
        NSError *unlinkError;
        if (![manager removeItemAtPath:folder error:&unlinkError]) {
            reject([@(unlinkError.code) stringValue], unlinkError.localizedDescription, unlinkError);
            return;
        }
    }

    if (![DCTar gzipDecompress:source toPath:folder error:nil]) {
      reject(@"-3", @"error while decompressing", nil);
       return;
    }

    resolve(@{@"path": folder});
}

@end
