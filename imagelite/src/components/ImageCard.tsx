"use client"
import Image from "next/image"

interface ImageCardProps {
    name?: string;
    size?: number;
    dataUpload?: string;
    src?: string;
    extension?: string;
}

export const ImageCard: React.FC<ImageCardProps> = ({
    name, dataUpload, src, size, extension
}: ImageCardProps) => {

    function download() {
        window.open(src, '_blank');
    }

    function formatBytes(bytes: number = 0, decimals = 2) {
        if (!+bytes) return '0 Bytes'

        const k = 1024
        const dm = decimals < 0 ? 0 : decimals
        const sizes = ['Bytes', 'KB', 'MB', 'GB']

        const i = Math.floor(Math.log(bytes) / Math.log(k))

        return `${parseFloat((bytes / Math.pow(k, i)).toFixed(dm))} ${sizes[i]}`
    }

    return (
        <div
            className="card relative bg-white rounded-md shadow-md transition-transform ease-in duration-300 transform hover:shadow-lg hover:-translate-y-2">
            <Image
                onClick={download}
                src={src || '/image_not.jpg'}
                width={1920}
                height={1080}
                className="h-56 w-full object-cover rounded-t-md cursor-pointer"
                alt=""
            />
            <div className="card-body p-4">
                <h5 className="text-xl font-semibold mb-2 text-gray-600">{name}</h5>
                <p className="text-gray-600">{extension}</p>
                <p className="text-gray-600">{formatBytes(size)}</p>
                <p className="text-gray-600">{dataUpload}</p>
            </div>
        </div>
    )
}